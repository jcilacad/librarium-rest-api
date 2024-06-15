package com.projects.loanservice.service.impl;

import com.projects.loanservice.client.BookServiceClient;
import com.projects.loanservice.dto.request.BookRequest;
import com.projects.loanservice.dto.request.LoanRequest;
import com.projects.loanservice.dto.response.BookResponse;
import com.projects.loanservice.dto.response.LoanResponse;
import com.projects.loanservice.entity.Loan;
import com.projects.loanservice.exception.BookNotAvailableException;
import com.projects.loanservice.exception.InvalidDueDateException;
import com.projects.loanservice.exception.LoanNotFoundException;
import com.projects.loanservice.mapper.BookMapper;
import com.projects.loanservice.mapper.LoanMapper;
import com.projects.loanservice.repository.LoanRepository;
import com.projects.loanservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanServiceImpl implements LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    private final LoanRepository loanRepository;
    private final BookServiceClient bookServiceClient;

    @Override
    @Transactional
    public LoanResponse createLoan(LoanRequest loanRequest) {
        if (loanRequest.getDueDate().isBefore(loanRequest.getLoanDate())) {
            throw new InvalidDueDateException(loanRequest.getDueDate(), loanRequest.getLoanDate());
        }

        Loan loan = LoanMapper.INSTANCE.loanRequestToLoan(loanRequest);
        Loan savedLoan = loanRepository.save(loan);
        BookResponse bookResponse = bookServiceClient.getBookById(savedLoan.getBookId());
        if (!bookResponse.isAvailable()) {
            throw new BookNotAvailableException(bookResponse.getId());
        }

        bookResponse.setAvailable(false);
        BookRequest bookRequest = BookMapper.INSTANCE.bookResponseToBookRequest(bookResponse);
        bookServiceClient.updateBook(bookResponse.getId(), bookRequest);
        logger.info("Loan created successfully with id : {}", savedLoan.getId());
        return LoanMapper.INSTANCE.loanToLoanResponse(savedLoan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(LoanMapper.INSTANCE::loanToLoanResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        logger.info("Found loan with id : {}", id);
        return loanRepository.findById(id)
                .map(LoanMapper.INSTANCE::loanToLoanResponse)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    @Override
    @Transactional
    public LoanResponse updateLoan(Long id, LoanRequest loanRequest) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        loan.setMemberId(loanRequest.getMemberId());
        loan.setBookId(loanRequest.getBookId());
        loan.setLoanDate(loanRequest.getLoanDate());
        loan.setDueDate(loanRequest.getDueDate());
        logger.info("Updated loan with id : {}", id);
        return LoanMapper.INSTANCE.loanToLoanResponse(loanRepository.save(loan));
    }

    @Override
    @Transactional
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));
        BookResponse bookResponse = bookServiceClient.getBookById(loan.getBookId());
        bookResponse.setAvailable(true);
        BookRequest bookRequest = BookMapper.INSTANCE.bookResponseToBookRequest(bookResponse);
        bookServiceClient.updateBook(bookResponse.getId(), bookRequest);
        logger.info("Returned book with book id : {}", bookResponse.getId());
        loanRepository.delete(loan);
        logger.info("Loan deleted with loan id : {}", loanId);
    }

    @Override
    public List<LoanResponse> getOverdueLoans() {
        return loanRepository.findAll()
                .stream()
                .filter(loan -> LocalDateTime.now().isAfter(loan.getDueDate()))
                .map(LoanMapper.INSTANCE::loanToLoanResponse)
                .collect(Collectors.toList());
    }
}

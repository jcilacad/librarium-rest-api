package com.projects.loanservice.service;

import com.projects.loanservice.dto.request.LoanRequest;
import com.projects.loanservice.dto.response.LoanResponse;

import java.util.List;

public interface LoanService {

    LoanResponse createLoan(LoanRequest loanRequest);

    List<LoanResponse> getAllLoans();

    LoanResponse getLoanById(Long id);

    LoanResponse updateLoan(Long id, LoanRequest loanRequest);

    void returnBook(Long loanId);

    List<LoanResponse> getOverdueLoans();
}

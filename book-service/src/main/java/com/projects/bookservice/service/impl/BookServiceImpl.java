package com.projects.bookservice.service.impl;

import com.projects.bookservice.dto.request.BookRequest;
import com.projects.bookservice.dto.response.BookResponse;
import com.projects.bookservice.entity.Book;
import com.projects.bookservice.exception.BookAlreadyExistsException;
import com.projects.bookservice.exception.BookNotFoundException;
import com.projects.bookservice.mapper.BookMapper;
import com.projects.bookservice.repository.BookRepository;
import com.projects.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        boolean existsByIsbn = bookRepository.existsByIsbn(bookRequest.getIsbn());
        if (existsByIsbn) {
            throw new BookAlreadyExistsException(bookRequest.getIsbn());
        }

        Book book = BookMapper.INSTANCE.bookRequestToBook(bookRequest);
        Book savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper.INSTANCE::bookToBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper.INSTANCE::bookToBookResponse)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setAvailable(bookRequest.isAvailable());
        Book updatedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookResponse(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
}

package com.projects.bookservice.service;

import com.projects.bookservice.dto.request.BookRequest;
import com.projects.bookservice.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest bookRequest);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    void deleteBook(Long id);
}
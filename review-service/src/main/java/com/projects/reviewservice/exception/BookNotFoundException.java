package com.projects.reviewservice.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super(String.format("Book not found with id : %s", bookId));
    }
}

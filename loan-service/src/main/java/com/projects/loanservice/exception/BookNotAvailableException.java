package com.projects.loanservice.exception;

public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(Long bookId) {
        super(String.format("Book not available with id : %s", bookId));
    }
}

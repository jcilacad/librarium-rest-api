package com.projects.bookservice.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super(String.format("Book not found with id : %s", id));
    }
}

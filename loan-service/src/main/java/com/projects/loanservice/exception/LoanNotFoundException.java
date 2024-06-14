package com.projects.loanservice.exception;

public class LoanNotFoundException extends RuntimeException{

    public LoanNotFoundException(Long id) {
        super(String.format("Loan not found with id : %s", id));
    }
}

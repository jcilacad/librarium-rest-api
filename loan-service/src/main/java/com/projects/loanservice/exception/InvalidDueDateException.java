package com.projects.loanservice.exception;

import java.time.LocalDateTime;

public class InvalidDueDateException extends RuntimeException {

    public InvalidDueDateException(LocalDateTime dueDate, LocalDateTime loanDate) {
        super(String.format("Due date : %t cannot be before the loan date : %t.", dueDate, loanDate));
    }
}

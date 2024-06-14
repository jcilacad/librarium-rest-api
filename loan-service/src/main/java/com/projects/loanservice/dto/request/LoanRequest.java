package com.projects.loanservice.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {
    @NotNull(message = "Member ID cannot be null")
    private Long memberId;
    @NotNull(message = "Book ID cannot be null")
    private Long bookId;
    @NotNull(message = "Loan date cannot be null")
    private LocalDateTime loanDate;
    @NotNull(message = "Due date cannot be null")
    @FutureOrPresent(message = "Due date must be in the future or present")
    private LocalDateTime dueDate;
}

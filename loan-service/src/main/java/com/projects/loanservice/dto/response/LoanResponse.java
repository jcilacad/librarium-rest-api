package com.projects.loanservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
}

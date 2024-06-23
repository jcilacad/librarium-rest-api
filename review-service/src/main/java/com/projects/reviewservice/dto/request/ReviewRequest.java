package com.projects.reviewservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    @NotNull(message = "Book id must not be empty")
    private Long bookId;
    @NotNull(message = "Member id must not be empty")
    private Long memberId;
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
    private String comment;
}

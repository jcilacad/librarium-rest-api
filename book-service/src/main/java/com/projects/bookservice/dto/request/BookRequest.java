package com.projects.bookservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @NotEmpty(message = "Author must not be empty")
    private String author;
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN")
    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;
    private boolean isAvailable;
}

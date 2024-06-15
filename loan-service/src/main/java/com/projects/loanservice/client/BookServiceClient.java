package com.projects.loanservice.client;

import com.projects.loanservice.dto.request.BookRequest;
import com.projects.loanservice.dto.response.BookResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BOOK-SERVICE", path = "/api/v1/books")
public interface BookServiceClient {

    @GetMapping("/{id}")
    BookResponse getBookById(@PathVariable Long id);

    @PutMapping("/{id}")
    BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest);
}

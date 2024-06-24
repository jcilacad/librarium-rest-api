package com.projects.reviewservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "BOOK-SERVICE", url = "http://localhost:8080/api/v1/books")
public interface BookServiceClient {

    @GetMapping("/validate/{id}")
    Boolean existsById(@PathVariable Long id);
}

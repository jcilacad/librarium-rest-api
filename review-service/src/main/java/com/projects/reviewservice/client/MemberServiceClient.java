package com.projects.reviewservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MEMBER-SERVICE", url = "http://localhost:8081/api/v1/members")
public interface MemberServiceClient {

    @GetMapping("/validate/{id}")
    Boolean existsById(@PathVariable Long id);
}

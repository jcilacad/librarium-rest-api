package com.projects.loanservice.client;

import com.projects.loanservice.dto.response.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEMBER-SERVICE", path = "/api/v1/members")
public interface MemberServiceClient {

    @GetMapping("/{id}")
    MemberResponse getMemberById(@PathVariable Long id);
}

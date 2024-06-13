package com.projects.memberservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    @NotEmpty(message = "First should not be empty")
    private String firstName;
    @NotEmpty(message = "First should not be empty")
    private String lastName;
    @Email
    @NotEmpty(message = "First should not be empty")
    private String email;
}

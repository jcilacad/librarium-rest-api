package com.projects.reviewservice.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long memberId) {
        super(String.format("Member not found with id : %s", memberId));
    }
}

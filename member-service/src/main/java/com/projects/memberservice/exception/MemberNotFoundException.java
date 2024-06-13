package com.projects.memberservice.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super(String.format("Member not found with id : %s", id));
    }
}

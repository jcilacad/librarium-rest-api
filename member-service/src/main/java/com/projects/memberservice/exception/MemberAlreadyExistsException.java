package com.projects.memberservice.exception;

public class MemberAlreadyExistsException extends RuntimeException {

    public MemberAlreadyExistsException(String email) {
        super(String.format("Member already exists with email : %s", email));
    }
}

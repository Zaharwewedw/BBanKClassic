package com.bank.profile.exception;

public class PassportNotFoundException extends RuntimeException {

    public PassportNotFoundException(String message) {
        super(message);
    }
}

package com.bank.profile.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message){
        super(message);
    }
}

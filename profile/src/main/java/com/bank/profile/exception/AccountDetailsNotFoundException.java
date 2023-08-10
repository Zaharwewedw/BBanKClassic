package com.bank.profile.exception;

public class AccountDetailsNotFoundException extends RuntimeException {

    public AccountDetailsNotFoundException(String message){
        super(message);
    }
}

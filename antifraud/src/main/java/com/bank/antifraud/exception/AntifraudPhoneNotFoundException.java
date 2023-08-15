package com.bank.antifraud.exception;

import lombok.Getter;

@Getter
public class AntifraudPhoneNotFoundException extends RuntimeException {

    public AntifraudPhoneNotFoundException (String message) {
        super(message);
    }
}

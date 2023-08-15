package com.bank.antifraud.exception;

import lombok.Getter;

@Getter
public class AntifraudCardNotFoundException extends RuntimeException{

    public AntifraudCardNotFoundException(String message) {
        super(message);
    }
}

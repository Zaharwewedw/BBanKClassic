package com.bank.antifraud.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AntifraudAccountNotFoundException extends RuntimeException {

    public AntifraudAccountNotFoundException (String message) {
        super(message);
    }
}

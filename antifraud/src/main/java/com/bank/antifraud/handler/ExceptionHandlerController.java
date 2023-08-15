package com.bank.antifraud.handler;

import com.bank.antifraud.dto.AntifraudSuspiciousAccountTransferDTO;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudCardNotFoundException;
import com.bank.antifraud.exception.AntifraudPhoneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    private ResponseEntity<String> accountException(AntifraudAccountNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<AntifraudCardNotFoundException> cardException(AntifraudCardNotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<AntifraudPhoneNotFoundException> phoneException(AntifraudPhoneNotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}

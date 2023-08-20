package com.bank.publicinfo.handler;

import com.bank.publicinfo.exception.branchException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BranchExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<BranchException> BranchException(BranchNotFoundException e) {
        BranchException exception = new BranchException("Branch not found", System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BranchException> BranchException(BranchNotSaveException e) {
        BranchException exception = new BranchException(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<AtmException> BranchException(AtmNotFoundException e) {
        AtmException exception = new AtmException("Atm not found", System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<AtmException> BranchException(AtmNotSaveException e) {
        AtmException exception = new AtmException(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}

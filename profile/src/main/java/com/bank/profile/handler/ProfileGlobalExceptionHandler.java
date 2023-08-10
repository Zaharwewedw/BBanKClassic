package com.bank.profile.handler;

import com.bank.profile.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ProfileGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(AccountDetailsNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(ActualRegistrationNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(PassportNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(ProfileNotFoundException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RegistrationNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }
}

package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.exception.ValidationErrorResponse;
import com.bank.account.exception.Violation;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class AccountDetailsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectAccountData> handleException(AccountDetailsNotFoundException exception) {
        IncorrectAccountData incorrectData = new IncorrectAccountData();
        incorrectData.setError(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectAccountData> handleException(MethodArgumentTypeMismatchException exception) {
        IncorrectAccountData incorrectData = new IncorrectAccountData();
        incorrectData.setError(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectAccountData> handleException(ConstraintViolationException exception)
    {
        IncorrectAccountData incorrectId = new IncorrectAccountData();
        incorrectId.setError(exception.getSQLException().toString());
        return new ResponseEntity<>(incorrectId, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ValidationErrorResponse(violations);
    }

}

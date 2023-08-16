package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

public class AccountDetailsExceptionHandlerTest {

    @Test
    void shouldHandleAccountDetailsNotFoundExceptionTest() {
        AccountDetailsExceptionHandler handler = new AccountDetailsExceptionHandler();
        ResponseEntity<IncorrectAccountData> response = handler.handleException(new AccountDetailsNotFoundException("Account not found"));
        Assertions.assertEquals("Account not found", response.getBody().getError());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldHandleMethodArgumentTypeMismatchExceptionTest() {
        AccountDetailsExceptionHandler handler = new AccountDetailsExceptionHandler();
        ResponseEntity<IncorrectAccountData> response = handler.handleException(new MethodArgumentTypeMismatchException(null, null, "test", null, null));
        Assertions.assertEquals("Failed to convert value of type 'null'", response.getBody().getError());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldHandleConstraintViolationExceptionTest() {
        AccountDetailsExceptionHandler handler = new AccountDetailsExceptionHandler();
        ResponseEntity<IncorrectAccountData> response = handler.handleException(new ConstraintViolationException("test", new SQLException(), "null"));
        Assertions.assertEquals("java.sql.SQLException", response.getBody().getError());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

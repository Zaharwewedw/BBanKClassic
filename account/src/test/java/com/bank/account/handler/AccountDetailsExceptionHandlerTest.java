package com.bank.account.handler;

import com.bank.account.exception.AccountDetailsNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("Account not found", response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldHandleMethodArgumentTypeMismatchExceptionTest() {
        AccountDetailsExceptionHandler handler = new AccountDetailsExceptionHandler();
        ResponseEntity<IncorrectAccountData> response = handler.handleException(new MethodArgumentTypeMismatchException(null, null, "test", null, null));
        assertEquals("Failed to convert value of type 'null'", response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldHandleConstraintViolationExceptionTest() {
        AccountDetailsExceptionHandler handler = new AccountDetailsExceptionHandler();
        ResponseEntity<IncorrectAccountData> response = handler.handleException(new ConstraintViolationException("test", new SQLException(), "null"));
        assertEquals("java.sql.SQLException", response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

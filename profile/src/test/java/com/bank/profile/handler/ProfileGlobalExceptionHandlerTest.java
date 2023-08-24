package com.bank.profile.handler;

import com.bank.profile.exception.AccountDetailsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProfileGlobalExceptionHandlerTest {

    @Test
    void handleException() {
        ProfileGlobalExceptionHandler handler = new ProfileGlobalExceptionHandler();
        String errorMessage = "Account details not found";

        ResponseEntity<ErrorMessage> response = handler.handleException(new AccountDetailsNotFoundException(errorMessage));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
}
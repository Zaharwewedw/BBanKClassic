package com.bank.profile.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "Passport not found";
        PassportNotFoundException exception = new PassportNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
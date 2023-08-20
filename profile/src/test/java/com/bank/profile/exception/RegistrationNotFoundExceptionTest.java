package com.bank.profile.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationNotFoundExceptionTest {
    @Test
    void testConstructor() {
        String errorMessage = "Registration not found";
        RegistrationNotFoundException exception = new RegistrationNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
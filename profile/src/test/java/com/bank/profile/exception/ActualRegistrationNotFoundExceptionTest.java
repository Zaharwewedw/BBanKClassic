package com.bank.profile.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActualRegistrationNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "Actual registration not found";
        ActualRegistrationNotFoundException exception = new ActualRegistrationNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
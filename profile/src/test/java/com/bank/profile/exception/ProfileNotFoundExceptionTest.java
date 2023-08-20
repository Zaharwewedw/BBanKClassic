package com.bank.profile.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "Profile not found";
        ProfileNotFoundException exception = new ProfileNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
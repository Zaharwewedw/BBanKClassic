package com.bank.profile.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDetailsNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "Account details not found";
        AccountDetailsNotFoundException exception = new AccountDetailsNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
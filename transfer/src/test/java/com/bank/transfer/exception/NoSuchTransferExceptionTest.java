package com.bank.transfer.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoSuchTransferExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Test exception message";
        NoSuchTransferException exception = new NoSuchTransferException(message);

        assertEquals(message, exception.getMessage());
    }
}
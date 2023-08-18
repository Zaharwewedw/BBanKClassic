package com.bank.history.exeption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryExceptionTest {

    @Test
    void testConstructorAndGetters(){
        String message = "warning";
        long timestamp = 15;
        HistoryException historyException = new HistoryException(message, timestamp);
        assertEquals(message, historyException.getMessage());
        assertEquals(timestamp, historyException.getTimestamp());

    }
    @Test
    void testSetters(){
        String message = "warning";
        long timestamp = 15;
        HistoryException historyException = new HistoryException("error",22);
        historyException.setMessage(message);
        historyException.setTimestamp(timestamp);
        assertEquals(message, historyException.getMessage());
        assertEquals(timestamp, historyException.getTimestamp());

    }
}

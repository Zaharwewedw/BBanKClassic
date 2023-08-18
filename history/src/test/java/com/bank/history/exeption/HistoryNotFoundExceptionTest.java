package com.bank.history.exeption;

import org.apache.http.HttpException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryNotFoundExceptionTest {
    @Test
    void testHistoryNotFoundException(){
        HistoryNotFoundException exception=new HistoryNotFoundException();
        assertEquals(null, exception.getMessage());

    }

}

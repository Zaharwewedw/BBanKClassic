package com.bank.history.hendler;
import com.bank.history.exeption.HistoryException;
import com.bank.history.exeption.HistoryNotFoundException;
import com.bank.history.servise.HistoryServise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ExceptionHandlerHistoryTest {
    @Mock
    private HistoryNotFoundException historyException;
    @InjectMocks
    private  ExceptionHandlerHistory exceptionHandlerHistory;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public  void  testExceptionHandlerHistory(){

        when(historyException.getMessage()).thenReturn("History not found");

        ResponseEntity<HistoryException> responseEntity = exceptionHandlerHistory.historyException(historyException);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("History not found", responseEntity.getBody().getMessage());
    }

}

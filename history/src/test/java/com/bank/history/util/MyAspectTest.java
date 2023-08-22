package com.bank.history.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MyAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private MyAspect myAspect;

    @Test
    public void testAroundGetHistoryList() throws Throwable {
        myAspect.aroundGetHistoryList(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    public void testAroundGetHistoryById() throws Throwable {
        myAspect.aroundGetHistoryById(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    public void testAroundDeleteHistoryById() throws Throwable {
        myAspect.aroundDeleteHistoryById(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    public void testAroundCreateHistory() throws Throwable {
        myAspect.aroundCreateHistory(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    public void testAroundUpdateHistoryById() throws Throwable {
        myAspect.aroundUpdateHistoryById(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
    }
}
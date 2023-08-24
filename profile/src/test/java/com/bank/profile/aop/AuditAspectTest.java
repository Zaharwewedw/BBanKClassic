package com.bank.profile.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuditAspectTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuditAspect auditAspect;

    @Test
    void testAuditRead() throws Throwable {
        String testResult = "testResult";
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);

        when(joinPoint.proceed()).thenReturn(testResult);
        auditAspect.auditRead(joinPoint);
        verify(joinPoint, times(1)).proceed();
    }

    @Test
    void testAuditAdd() throws Throwable {
        String testResult = "testResult";
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);

        when(joinPoint.proceed()).thenReturn(testResult);
        auditAspect.auditAdd(joinPoint);
        verify(joinPoint, times(1)).proceed();
    }

    @Test
    void testAuditUpdate() throws Throwable {
        String testResult = "testResult";
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);

        when(joinPoint.proceed()).thenReturn(testResult);
        auditAspect.auditUpdate(joinPoint);
        verify(joinPoint, times(1)).proceed();
    }

    @Test
    void testAuditDelete() throws Throwable {
        String testResult = "testResult";
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);

        when(joinPoint.proceed()).thenReturn(testResult);
        auditAspect.auditDelete(joinPoint);
        verify(joinPoint, times(1)).proceed();
    }
}
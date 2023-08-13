package com.bank.transfer.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuditAspectTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private AuditAspect auditAspect;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getDeclaringTypeName()).thenReturn("com.bank.transfer.service.TransferServiceImpl");
    }

    @Test
    public void testAuditCreate() throws Throwable {
        String returnValue = "testValue";
        when(joinPoint.proceed()).thenReturn(returnValue);
        when(objectMapper.writeValueAsString(returnValue)).thenReturn("\"testValue\"");

        auditAspect.auditCreate(joinPoint);

        verify(jdbcTemplate).update(eq("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)"), eq("Transfer"), eq("POST"), eq("User"), any(Timestamp.class), eq("\"testValue\""));
    }

    @Test
    public void testAuditRead() throws Throwable {
        String returnValue = "testValue";
        when(joinPoint.proceed()).thenReturn(returnValue);
        when(objectMapper.writeValueAsString(returnValue)).thenReturn("\"testValue\"");

        auditAspect.auditRead(joinPoint);

        verify(jdbcTemplate).update(eq("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)"), eq("Transfer"), eq("GET"), eq("User"), any(Timestamp.class), eq("\"testValue\""));
    }

    @Test
    public void testAuditUpdate() throws Throwable {
        String returnValue = "testValue";
        when(joinPoint.proceed()).thenReturn(returnValue);
        when(objectMapper.writeValueAsString(returnValue)).thenReturn("\"testValue\"");

        auditAspect.auditUpdate(joinPoint);

        verify(jdbcTemplate).update(eq("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?)"), eq("Transfer"), eq("UPDATE"), eq("User"), any(Timestamp.class), eq("\"testValue\""), eq("\"testValue\""));
    }

    @Test
    public void testAuditDelete() throws Throwable {
        String returnValue = "testValue";
        when(joinPoint.proceed()).thenReturn(returnValue);
        when(objectMapper.writeValueAsString(returnValue)).thenReturn("\"testValue\"");

        auditAspect.auditDelete(joinPoint);

        verify(jdbcTemplate).update(eq("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)"), eq("Transfer"), eq("DELETE"), eq("User"), any(Timestamp.class), eq("\"testValue\""));
    }
}
package com.bank.profile.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Aspect
@Component
public class AuditAspect {

    private JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuditAspect(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Around("execution(* com.bank.profile.service.*.*.get*(..))")
    public Object auditRead(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "profile.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                returnVal.getClass().getName(), "GET", "User", new Timestamp(System.currentTimeMillis()), objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }

    @Around("execution(* com.bank.profile.service.*.*.add*(..))")
    public Object auditAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "profile.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                returnVal.getClass().getName(), "POST", "User", new Timestamp(System.currentTimeMillis()), objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }

    @Around("execution(* com.bank.profile.service.*.*.update*(..))")
    public Object auditUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "profile.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                returnVal.getClass().getName(), "PUT", "User", new Timestamp(System.currentTimeMillis()), objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }

    @Around("execution(* com.bank.profile.service.*.*.delete*(..))")
    public Object auditDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "profile.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                returnVal.getClass().getName(), "DELETE", "User", new Timestamp(System.currentTimeMillis()), objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }


}

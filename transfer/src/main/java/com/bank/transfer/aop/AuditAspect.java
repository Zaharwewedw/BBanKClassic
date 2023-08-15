package com.bank.transfer.aop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

//Класс, который аудирует методы сервисного слоя с записью информации в базу данных(в таблицу audit)
@Aspect
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Component
public class AuditAspect {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuditAspect(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    //Метод, который дает имя сущности через работу со строками
    private String getEntityName(ProceedingJoinPoint joinPoint) {
        String serviceName = joinPoint.getSignature().getDeclaringTypeName();
        String[] parts = serviceName.split("\\.");
        String className = parts[parts.length - 1];
        className = className.replace("Impl", "");
        className = className.replace("Service", "");
        return className;
    }

    //Метод аудирует все методы сервисного слоя, где создаются новые переводы
    @Around("execution(* com.bank.transfer.service.*.create*(..))")
    public Object auditCreate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        String entityName = getEntityName(joinPoint);
        String entityJson = objectMapper.writeValueAsString(returnValue);
        jdbcTemplate.update("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                entityName, "POST", "User", new Timestamp(System.currentTimeMillis()), entityJson);

        return returnValue;
    }

    //Метод аудирует все методы сервисного слоя, которые показывают данные о переводах
    @Around("execution(* com.bank.transfer.service.*.get*(..))")
    public Object auditRead(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        String entityName = getEntityName(joinPoint);
        String entityJson = objectMapper.writeValueAsString(returnValue);
        jdbcTemplate.update("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                entityName, "GET", "User", new Timestamp(System.currentTimeMillis()), entityJson);

        return returnValue;
    }

    //Метод аудирует все методы сервисного слоя, где исправляется цель переводов
    @Around("execution(* com.bank.transfer.service.*.update*(..))")
    public Object auditUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        String entityName = getEntityName(joinPoint);
        String newEntityJson = objectMapper.writeValueAsString(returnValue);
        jdbcTemplate.update("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?)",
                entityName, "UPDATE", "User", new Timestamp(System.currentTimeMillis()), newEntityJson, newEntityJson);

        return returnValue;
    }

    //Метод аудирует все методы сервисного слоя, где удаляются переводы
    @Around("execution(* com.bank.transfer.service.*.delete*(..))")
    public Object auditDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        String entityName = getEntityName(joinPoint);
        String entityJson = objectMapper.writeValueAsString(returnValue);
        jdbcTemplate.update("INSERT INTO transfer.audit (entity_type, operation_type, created_by, created_at, entity_json) VALUES (?, ?, ?, ?, ?)",
                entityName, "DELETE", "User", new Timestamp(System.currentTimeMillis()), entityJson);
        return returnValue;
    }
}
package com.bank.publicinfo.util;

import com.bank.publicinfo.repository.bankDetailsRepository.BankDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Aspect
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PublicInfoAspect {

    final JdbcTemplate jdbcTemplate;
    final ObjectMapper objectMapper;
    final BankDetailsRepository bankDetailsRepository;

    @Around("execution(* com.bank.publicinfo.service.*.*.get*(..))")
    public Object auditGet(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "public_bank_information.audit (entity_type, operation_type, created_by, modified_by" +
                        ", created_at, modified_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                returnVal.getClass().getSimpleName(), "GET", "User", null
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow")), null, null
                , objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }

    @Around("execution(* com.bank.publicinfo.service.*.*.save*(..))")
    public Object auditSave(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "public_bank_information.audit (entity_type, operation_type, created_by, modified_by" +
                        ", created_at, modified_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                returnVal.getClass().getSimpleName(), "POST", "User", null
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow")), null, null
                , objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }

    @Around("execution(* com.bank.publicinfo.service.*.*.update*(..))")
    public Object auditUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = (Long) joinPoint.getArgs()[0];
        String old = objectMapper.writeValueAsString(bankDetailsRepository.findById(id).orElse(null));
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "public_bank_information.audit (entity_type, operation_type, created_by, modified_by" +
                        ", created_at, modified_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                returnVal.getClass().getSimpleName(), "PUT", "User", "User"
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow"))
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow"))
                , objectMapper.writeValueAsString(returnVal)
                , old);
        return returnVal;
    }

    @Around(("execution(* com.bank.publicinfo.service.*.*.delete*(..))"))
    public Object auditDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnVal = joinPoint.proceed();
        jdbcTemplate.update("INSERT INTO " +
                        "public_bank_information.audit (entity_type, operation_type, created_by, modified_by" +
                        ", created_at, modified_at, new_entity_json, entity_json) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                returnVal.getClass().getSimpleName(), "DELETE", "User", null
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow"))
                , OffsetDateTime.now(ZoneId.of("Europe/Moscow"))
                , null, objectMapper.writeValueAsString(returnVal));
        return returnVal;
    }
}
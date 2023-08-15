package com.bank.antifraud.util.aspect;

import com.bank.antifraud.service.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Aspect
public class AuditAspect {

    private final AuditService auditService;

    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }


    @Around("execution(* com.bank.antifraud.service.*.save(*))")
    public void createAroundAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object newEntity = args[0];
        auditService.save(newEntity.getClass().getSimpleName(), "save", "user",
                null, new Timestamp(System.currentTimeMillis()), new Timestamp(0)
                , null, newEntity.toString());
    }
    @Around("execution(* com.bank.antifraud.service.*.update(*))")
    public void updateAroundAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object newEntity = args[0];
        auditService.save(newEntity.getClass().getSimpleName(), "update", "user",
                "user", new Timestamp(System.currentTimeMillis()), new Timestamp(0)
                , newEntity.toString(), newEntity.toString());
    }
}

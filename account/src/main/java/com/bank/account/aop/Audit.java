package com.bank.account.aop;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.entity.AuditEntity;
import com.bank.account.factory.AccountDtoToEntityFactory;
import com.bank.account.service.AccountDetailsService;
import com.bank.account.service.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Aspect
public class Audit {

    @Autowired
    AuditService service;

    @Autowired
    AccountDetailsService accountDetailsService;

    @Autowired
    AccountDtoToEntityFactory accountDtoToEntityFactory;

    @Around("execution(public * addAccountDetails(..))")
    public AccountDetailsEntity addMethodAudit(ProceedingJoinPoint jp) throws Throwable {
        OffsetDateTime time = OffsetDateTime.now();
        AccountDetailsEntity entity = (AccountDetailsEntity) jp.proceed();
        AuditEntity auditEntity = AuditEntity.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("create")
                .createdBy("User")
                .modifiedBy("User")
                .modifiedAt(OffsetDateTime.now())
                .createdAt(time)
                .entityJson(entity.toString())
                .build();
        service.add(auditEntity);
        return entity;
    }

    @Around("execution(public * deleteAccountDetails(long))")
    public AccountDetailsEntity deleteMethodAudit(ProceedingJoinPoint jp) throws Throwable {
        AccountDetailsEntity result = (AccountDetailsEntity) jp.proceed();
        AuditEntity auditEntity = AuditEntity.builder()
                .entityType(result.getClass().getSimpleName())
                .operationType("delete")
                .createdBy("User")
                .modifiedBy("User")
                .modifiedAt(OffsetDateTime.now())
                .createdAt(OffsetDateTime.now())
                .entityJson(result.toString())
                .newEntityJson(null)
                .build();
        service.add(auditEntity);
        return result;
    }

    @Around("execution(public * updateAccountDetails(..))")
    public AccountDetailsEntity updateMethodAudit(ProceedingJoinPoint jp) throws Throwable {
        AccountDetailsEntity sourceEntity = accountDetailsService.getAccountDetailsById(((AccountDetailsEntity) jp.getArgs()[0]).getId());
        String sourceEntityJson = sourceEntity.toString();
        AccountDetailsEntity resultEntity = (AccountDetailsEntity) jp.proceed();
        AuditEntity auditEntity = AuditEntity.builder()
                .entityType(resultEntity.getClass().getSimpleName())
                .operationType("edit")
                .createdBy("User")
                .modifiedBy("User")
                .createdAt(OffsetDateTime.now())
                .modifiedAt(OffsetDateTime.now())
                .newEntityJson(resultEntity.toString())
                .entityJson(sourceEntityJson)
                .build();
        service.add(auditEntity);
        return resultEntity;
    }
}

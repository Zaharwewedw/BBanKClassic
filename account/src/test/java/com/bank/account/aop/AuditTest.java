package com.bank.account.aop;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.service.AccountDetailsService;
import com.bank.account.service.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AuditTest {

    @Mock
    private ProceedingJoinPoint jp;

    @Mock
    private AuditService auditService;

    @Mock
    private AccountDetailsService accountDetailsService;

    @InjectMocks
    Audit audit;
    @Test
    void shouldAddMethodAuditTest() throws Throwable {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        Mockito.when(jp.proceed()).thenReturn(entity);
        audit.addMethodAudit(jp);
        Mockito.verify(jp, Mockito.times(1)).proceed();
        Mockito.verify(auditService, Mockito.times(1)).add(Mockito.any());
    }

    @Test
    void shouldDeleteMethodAuditTest() throws Throwable {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        Mockito.when(jp.proceed()).thenReturn(entity);
        audit.deleteMethodAudit(jp);
        Mockito.verify(jp, Mockito.times(1)).proceed();
        Mockito.verify(auditService, Mockito.times(1)).add(Mockito.any());
    }

    @Test
    void shouldUpdateMethodAuditTest() throws Throwable {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        Mockito.when(jp.getArgs()).thenReturn(new AccountDetailsEntity[]{AccountDetailsEntity.builder().id(1L).build()});
        Mockito.when(jp.proceed()).thenReturn(entity);
        Mockito.when(accountDetailsService.getAccountDetailsById(1L)).thenReturn(AccountDetailsEntity.builder().id(1L).build());
        audit.updateMethodAudit(jp);
        Mockito.verify(jp, Mockito.times(1)).proceed();
        Mockito.verify(auditService, Mockito.times(1)).add(Mockito.any());
        Mockito.verify(jp, Mockito.times(1)).getArgs();
    }
}

package com.bank.account.aop;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.service.AccountDetailsService;
import com.bank.account.service.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


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
        when(jp.proceed()).thenReturn(entity);
        audit.addMethodAudit(jp);
        verify(jp, times(1)).proceed();
        verify(auditService, times(1)).add(any());
    }

    @Test
    void shouldDeleteMethodAuditTest() throws Throwable {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        when(jp.proceed()).thenReturn(entity);
        audit.deleteMethodAudit(jp);
        verify(jp, times(1)).proceed();
        verify(auditService, times(1)).add(any());
    }

    @Test
    void shouldUpdateMethodAuditTest() throws Throwable {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        when(jp.getArgs()).thenReturn(new AccountDetailsEntity[]{AccountDetailsEntity.builder().id(1L).build()});
        when(jp.proceed()).thenReturn(entity);
        when(accountDetailsService.getAccountDetailsById(1L)).thenReturn(AccountDetailsEntity.builder().id(1L).build());
        audit.updateMethodAudit(jp);
        verify(jp, times(1)).proceed();
        verify(auditService, times(1)).add(any());
        verify(jp, times(1)).getArgs();
    }
}

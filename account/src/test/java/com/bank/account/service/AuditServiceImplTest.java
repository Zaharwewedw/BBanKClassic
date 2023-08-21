package com.bank.account.service;

import com.bank.account.entity.AuditEntity;
import com.bank.account.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
public class AuditServiceImplTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditServiceImpl auditService;

    @Test
    void shouldAdd() {
        OffsetDateTime data = OffsetDateTime.now();
        AuditEntity auditEntity = AuditEntity.builder()
                        .id(1L)
                        .entityJson("entityJson")
                        .newEntityJson("newEntityJson")
                        .entityType("account")
                        .modifiedAt(data)
                        .createdAt(data)
                        .operationType("create")
                        .createdBy("user")
                        .build();
        when(auditRepository.saveAndFlush(auditEntity)).thenReturn(auditEntity);
        auditService.add(auditEntity);
        verify(auditRepository, times(1)).saveAndFlush(auditEntity);
    }
}

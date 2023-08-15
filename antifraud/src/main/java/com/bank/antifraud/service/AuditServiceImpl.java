package com.bank.antifraud.service;

import com.bank.antifraud.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void save(String entityType, String operationType, String createdBy, String modifiedBy
            , Timestamp createdAt, Timestamp modifiedAt, String newEntityJson, String entityJson) {
        auditRepository.insertUser(entityType, operationType, createdBy, modifiedBy
                , createdAt, modifiedAt, newEntityJson, entityJson);
    }
}

package com.bank.antifraud.service;

import java.sql.Timestamp;

public interface AuditService {
    void save(String entityType, String operationType, String createdBy, String modifiedBy
            , Timestamp createdAt, Timestamp modifiedAt, String newEntityJson, String entityJson);
}

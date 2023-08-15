package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AntifraudAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface AuditRepository extends JpaRepository<AntifraudAudit, Long> {

    @Modifying
    @Query(
            value =
                    "insert into anti_fraud.audit (entity_type, operation_type, created_by" +
                            ", modified_by, created_at, modified_at" +
                            ", new_entity_json, entity_json) " +
                            "values (:entity_type, :operation_type, :created_by, :modified_by" +
                            ", :created_at, :modified_at, :new_entity_json, :entity_json)",
            nativeQuery = true)
    void insertUser(@Param("entity_type") String entityType, @Param("operation_type") String operationType,
                    @Param("created_by") String createdBy, @Param("modified_by") String modifiedBy
                    , @Param("created_at") Timestamp createdAt, @Param("modified_at") Timestamp modifiedAt
                    , @Param("new_entity_json") String newEntityJson, @Param("entity_json") String entityJson);
}

package com.bank.antifraud.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name = "audit")
@Data
public class AntifraudAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "createdAt")
    private long createdAt;

    @Column(name = "modified_at")
    private long modifiedAt;

    @Column(name = "new_entity_json")
    private long newEntityJson;

    @Column(name = "entity_json")
    private long entityJson;
}

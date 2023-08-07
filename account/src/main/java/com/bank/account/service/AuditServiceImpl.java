package com.bank.account.service;

import com.bank.account.entity.AuditEntity;
import com.bank.account.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditRepository repository;
    @Override
    @Transactional
    public void add(AuditEntity entity) {
        repository.saveAndFlush(entity);
    }
}

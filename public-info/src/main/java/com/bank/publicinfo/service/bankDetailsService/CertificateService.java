package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> getAll(Long id);

    Certificate getById(Long idCertificate);

    Certificate save(Long id, Certificate certificate);

    Certificate update(Long idCertificate, Certificate certificate);

    Certificate delete(Long idCertificate);
}

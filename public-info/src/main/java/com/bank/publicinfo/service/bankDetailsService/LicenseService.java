package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.License;

import java.util.List;

public interface LicenseService {
    List<License> getAll(Long id);

    License getById(Long idLicense);

    License save(Long id, License license);

    License update(Long idLicense, License license);

    License delete(Long idLicense);
}

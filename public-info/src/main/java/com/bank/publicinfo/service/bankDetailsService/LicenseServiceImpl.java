package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;
import com.bank.publicinfo.entity.bankDetailsEntity.License;
import com.bank.publicinfo.exception.bankDetailsException.LicenseNotFoundException;
import com.bank.publicinfo.repository.bankDetailsRepository.LicenseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LicenseServiceImpl implements LicenseService {
    final BankDetailsService bankDetailsService;
    final LicenseRepository licenseRepository;

    @Override
    public List<License> getAll(Long id) {
        return bankDetailsService.getById(id).getLicenseList();
    }

    @Override
    public License getById(Long idLicense) throws LicenseNotFoundException {
        return licenseRepository.findById(idLicense).orElseThrow(LicenseNotFoundException::new);
    }

    @Override
    @Transactional
    public License save(Long id, License license) {
        BankDetails bankDetails = bankDetailsService.getById(id);
        license.setBankDetailsId(bankDetails);
        bankDetails.getLicenseList().add(license);
        return licenseRepository.save(license);
    }

    @Override
    @Transactional
    public License update(Long idLicense, License nweLicense) {
        License license = getById(idLicense);
        license.setPhoto(nweLicense.getPhoto());
        return licenseRepository.save(license);
    }

    @Override
    @Transactional
    public License delete(Long idLicense) {
        License license = getById(idLicense);
        licenseRepository.deleteById(idLicense);
        return license;
    }
}
package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;
import com.bank.publicinfo.entity.bankDetailsEntity.Certificate;
import com.bank.publicinfo.entity.bankDetailsEntity.License;
import com.bank.publicinfo.exception.bankDetailsException.BankDetailsNotFoundException;
import com.bank.publicinfo.repository.bankDetailsRepository.BankDetailsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDetailServiceImpl implements BankDetailsService {
    final BankDetailsRepository bankDetailsRepository;

    @Override
    public List<BankDetails> getAll() {
        return bankDetailsRepository.findAll();
    }

    @Override
    public BankDetails getById(Long id) throws BankDetailsNotFoundException {
        return bankDetailsRepository.findById(id)
                .orElseThrow(BankDetailsNotFoundException::new);
    }

    @Override
    @Transactional
    public BankDetails save(BankDetails bankDetails) {
        List<License> licenseList = bankDetails.getLicenseList();
        if (licenseList != null) {
            for (License license : licenseList) {
                license.setBankDetailsId(bankDetails);
            }
        }
        List<Certificate> certificateList = bankDetails.getCertificateList();
        certificateList.forEach(certificate -> certificate.setBankDetailsId(bankDetails));
        return bankDetailsRepository.save(bankDetails);
    }

    @Override
    @Transactional
    public BankDetails update(Long id, BankDetails newBankDetails) {
        BankDetails oldBankDetails = getById(id);
        if (newBankDetails.getBik() != null) oldBankDetails.setBik(newBankDetails.getBik());
        if (newBankDetails.getInn() != null) oldBankDetails.setInn(newBankDetails.getInn());
        if (newBankDetails.getKpp() != null) oldBankDetails.setKpp(newBankDetails.getKpp());
        if (newBankDetails.getCorAccount() != null) oldBankDetails.setCorAccount(newBankDetails.getCorAccount());
        if (newBankDetails.getCity() != null) oldBankDetails.setCity(newBankDetails.getCity());
        if (newBankDetails.getJointStockCompany() != null)
            oldBankDetails.setJointStockCompany(newBankDetails.getJointStockCompany());
        if (newBankDetails.getName() != null) oldBankDetails.setName(newBankDetails.getName());
        List<License> newLicenseList = newBankDetails.getLicenseList();
        if (newLicenseList != null) {
            newLicenseList.forEach(license -> license.setBankDetailsId(oldBankDetails));
            if (oldBankDetails.getLicenseList() == null) oldBankDetails.setLicenseList(new ArrayList<>());
            oldBankDetails.getLicenseList().addAll(newLicenseList);
        }
        List<Certificate> newCertificateList = newBankDetails.getCertificateList();
        if (newCertificateList != null) {
            newCertificateList.forEach(certificate -> certificate.setBankDetailsId(oldBankDetails));
            if (oldBankDetails.getCertificateList() == null) oldBankDetails.setCertificateList(new ArrayList<>());
            oldBankDetails.getCertificateList().addAll(newCertificateList);
        }
        return bankDetailsRepository.save(oldBankDetails);
    }

    @Override
    @Transactional
    public BankDetails delete(Long id) {
        BankDetails bankDetails = getById(id);
        bankDetailsRepository.deleteById(id);
        return bankDetails;
    }
}
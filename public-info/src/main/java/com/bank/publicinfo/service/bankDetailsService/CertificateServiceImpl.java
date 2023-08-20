package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;
import com.bank.publicinfo.entity.bankDetailsEntity.Certificate;
import com.bank.publicinfo.exception.bankDetailsException.CertificateNotFoundException;
import com.bank.publicinfo.repository.bankDetailsRepository.CertificateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateServiceImpl implements CertificateService {
    final BankDetailsService bankDetailsService;
    final CertificateRepository certificateRepository;

    @Override
    public List<Certificate> getAll(Long id) {
        return bankDetailsService.getById(id).getCertificateList();
    }

    @Override
    public Certificate getById(Long idCertificate) throws CertificateNotFoundException {
        return certificateRepository.findById(idCertificate).orElseThrow(CertificateNotFoundException::new);
    }

    @Override
    @Transactional
    public Certificate save(Long id, Certificate certificate) {
        BankDetails bankDetails = bankDetailsService.getById(id);
        certificate.setBankDetailsId(bankDetails);
        bankDetails.getCertificateList().add(certificate);
        return certificateRepository.save(certificate);
    }

    @Override
    @Transactional
    public Certificate update(Long idCertificate, Certificate newCertificate) {
        Certificate certificate = getById(idCertificate);
        certificate.setPhoto(newCertificate.getPhoto());
        return certificateRepository.save(certificate);
    }

    @Override
    @Transactional
    public Certificate delete(Long idCertificate) {
        Certificate certificate = getById(idCertificate);
        certificateRepository.deleteById(idCertificate);
        return certificate;
    }
}
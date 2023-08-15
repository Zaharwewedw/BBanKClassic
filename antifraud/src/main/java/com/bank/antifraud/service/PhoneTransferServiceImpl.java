package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousPhoneTransfer;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudPhoneNotFoundException;
import com.bank.antifraud.repository.PhoneTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneTransferServiceImpl implements PhoneTransferService{

    @Autowired
    public PhoneTransferRepository phoneTransferRepository;

    public List<AntifraudSuspiciousPhoneTransfer> getAll() {
        return phoneTransferRepository.findAll();
    }

    @Transactional
    public void save(AntifraudSuspiciousPhoneTransfer s) {
        phoneTransferRepository.save(s);
    }

    public Optional<AntifraudSuspiciousPhoneTransfer> findById(long id) {
        return phoneTransferRepository.findById(id);
    }

    @Transactional
    public void deleteById(long id) {
        phoneTransferRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AntifraudSuspiciousPhoneTransfer update(long id, AntifraudSuspiciousPhoneTransfer s) {
        phoneTransferRepository.findById(id)
                .orElseThrow(() -> new AntifraudPhoneNotFoundException("no record with this id"));
        s.setId(id);
        AntifraudSuspiciousPhoneTransfer updatedPhoneTransfer = phoneTransferRepository.save(s);
        return updatedPhoneTransfer;
    }

}

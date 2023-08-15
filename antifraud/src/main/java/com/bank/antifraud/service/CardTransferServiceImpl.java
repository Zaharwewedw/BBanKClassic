package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousCardTransfer;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudCardNotFoundException;
import com.bank.antifraud.repository.CardTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardTransferServiceImpl implements CardTransferService{

    @Autowired
    public CardTransferRepository cardTransferRepository;
    

    public List<AntifraudSuspiciousCardTransfer> getAll() {
        return cardTransferRepository.findAll();
    }

    @Transactional
    public void save(AntifraudSuspiciousCardTransfer s) {
        cardTransferRepository.save(s);
    }

    public Optional<AntifraudSuspiciousCardTransfer> findById(long id) {
        return cardTransferRepository.findById(id);
    }

    @Transactional
    public void deleteById(long id) {
        cardTransferRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AntifraudSuspiciousCardTransfer update(long id, AntifraudSuspiciousCardTransfer s) {
        cardTransferRepository.findById(id)
                .orElseThrow(() -> new AntifraudCardNotFoundException("no record with this id"));
        s.setId(id);
        AntifraudSuspiciousCardTransfer updatedCardTransfer = cardTransferRepository.save(s);
        return updatedCardTransfer;
    }

}

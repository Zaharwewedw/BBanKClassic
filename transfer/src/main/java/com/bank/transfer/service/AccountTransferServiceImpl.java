package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

//Сервис с реализацией КРУД операций у сущности AccountTransfer
@Service
@Transactional
@RequiredArgsConstructor
public class AccountTransferServiceImpl implements AccountTransferService {
    private final AccountTransferMapper accountTransferMapper;
    private final AccountTransferRepository accountTransferRepository;

    @Override
    public AccountTransferDTO createTransfer(AccountTransferDTO accountTransferDTO) {
        AccountTransfer accountTransfer = accountTransferMapper.mapToEntity(accountTransferDTO);
        return accountTransferMapper.mapToDTO(accountTransferRepository.save(accountTransfer));
    }

    @Override
    public AccountTransferDTO getById(Long id) {
        return accountTransferMapper.mapToDTO(accountTransferRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transfer not found")));
    }

    @Override
    public List<AccountTransferDTO> getAll() {
        List<AccountTransfer> accountTransfers = accountTransferRepository.findAll();
        return accountTransfers.stream().map(accountTransferMapper::mapToDTO).collect(Collectors.toList());
    }

//    У перевода можно исправить только цель
    @Override
    public AccountTransferDTO updateAccountTransfer(Long id, AccountTransferDTO accountTransferDTO) {
        AccountTransfer accountTransfer = accountTransferRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transfer not found"));
        accountTransfer.setPurpose(accountTransferDTO.getPurpose());
        return accountTransferMapper.mapToDTO(accountTransferRepository.save(accountTransfer));
    }

    @Override
    public void deleteAccountTransfer(Long id) {
        AccountTransfer accountTransfer = accountTransferRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transfer not found"));
        accountTransferRepository.delete(accountTransfer);
    }
}

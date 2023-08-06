package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

//Сервис с реализацией КРУД операций у сущности PhoneTransfer
@Service
@Transactional
@RequiredArgsConstructor
public class PhoneTransferServiceImpl implements PhoneTransferService {
    private final PhoneTransferMapper phoneTransferMapper;
    private final PhoneTransferRepository phoneTransferRepository;

    @Override
    public PhoneTransferDTO createTransfer(PhoneTransferDTO phoneTransferDTO) {
        PhoneTransfer phoneTransfer = phoneTransferMapper.mapToEntity(phoneTransferDTO);
        return phoneTransferMapper.mapToDTO(phoneTransferRepository.save(phoneTransfer));
    }

    @Override
    public PhoneTransferDTO getById(Long id) {
        return phoneTransferMapper.mapToDTO(phoneTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found")));
    }

    @Override
    public List<PhoneTransferDTO> getAll() {
        List<PhoneTransfer> phoneTransfers = phoneTransferRepository.findAll();
        return phoneTransfers.stream().map(phoneTransferMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PhoneTransferDTO updatePhoneTransfer(Long id, PhoneTransferDTO phoneTransferDTO) {
        PhoneTransfer phoneTransfer = phoneTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found"));
        phoneTransfer.setPurpose(phoneTransferDTO.getPurpose());
        return phoneTransferMapper.mapToDTO(phoneTransferRepository.save(phoneTransfer));
    }

    @Override
    public void deletePhoneTransfer(Long id) {
        PhoneTransfer phoneTransfer = phoneTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found"));
        phoneTransferRepository.delete(phoneTransfer);

    }
}

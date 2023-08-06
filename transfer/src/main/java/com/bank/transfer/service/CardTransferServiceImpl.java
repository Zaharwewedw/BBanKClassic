package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

//Сервис с реализацией КРУД операций у сущности CardTransfer
@Service
@Transactional
@RequiredArgsConstructor
public class CardTransferServiceImpl implements CardTransferService {
    private final CardTransferMapper cardTransferMapper;
    private final CardTransferRepository cardTransferRepository;

    @Override
    public CardTransferDTO createTransfer(CardTransferDTO cardTransferDTO) {
        CardTransfer cardTransfer = cardTransferMapper.mapToEntity(cardTransferDTO);
        return cardTransferMapper.mapToDTO(cardTransferRepository.save(cardTransfer));
    }

    @Override
    public CardTransferDTO getById(Long id) {
        return cardTransferMapper.mapToDTO(cardTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found")));
    }

    @Override
    public List<CardTransferDTO> getAll() {
        List<CardTransfer> cardTransfers = cardTransferRepository.findAll();
        return cardTransfers.stream().map(cardTransferMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CardTransferDTO updateCardTransfer(Long id, CardTransferDTO cardTransferDTO) {
        CardTransfer cardTransfer = cardTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found"));
        cardTransfer.setPurpose(cardTransferDTO.getPurpose());
        return cardTransferMapper.mapToDTO(cardTransferRepository.save(cardTransfer));
    }

    @Override
    public void deleteCardTransfer(Long id) {
        CardTransfer cardTransfer = cardTransferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transfer not found"));
        cardTransferRepository.delete(cardTransfer);

    }
}

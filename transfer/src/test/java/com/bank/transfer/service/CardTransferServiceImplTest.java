package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CardTransferServiceImplTest {

    @Mock
    private CardTransferMapper cardTransferMapper;

    @Mock
    private CardTransferRepository cardTransferRepository;

    @InjectMocks
    private CardTransferServiceImpl cardTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransferTest() {
        CardTransferDTO dto = new CardTransferDTO();
        CardTransfer entity = new CardTransfer();

        when(cardTransferMapper.mapToEntity(dto)).thenReturn(entity);
        when(cardTransferRepository.save(entity)).thenReturn(entity);
        when(cardTransferMapper.mapToDTO(entity)).thenReturn(dto);

        cardTransferService.createTransfer(dto);

        verify(cardTransferMapper).mapToEntity(dto);
        verify(cardTransferRepository).save(entity);
        verify(cardTransferMapper).mapToDTO(entity);
    }

    @Test
    void getByIdTest() {
        Long id = 1L;
        CardTransfer entity = new CardTransfer();
        CardTransferDTO dto = new CardTransferDTO();

        when(cardTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(cardTransferMapper.mapToDTO(entity)).thenReturn(dto);

        cardTransferService.getById(id);

        verify(cardTransferRepository).findById(id);
        verify(cardTransferMapper).mapToDTO(entity);
    }

    @Test
    void getAllTest() {
        List<CardTransfer> entities = Arrays.asList(new CardTransfer(), new CardTransfer());
        List<CardTransferDTO> dtos = Arrays.asList(new CardTransferDTO(), new CardTransferDTO());

        when(cardTransferRepository.findAll()).thenReturn(entities);
        when(cardTransferMapper.mapToDTO(any(CardTransfer.class))).thenReturn(dtos.get(0), dtos.get(1));

        cardTransferService.getAll();

        verify(cardTransferRepository).findAll();
        verify(cardTransferMapper, times(2)).mapToDTO(any(CardTransfer.class));
    }

    @Test
    void updateCardTransferTest() {
        Long id = 1L;
        CardTransfer entity = new CardTransfer();
        CardTransferDTO dto = new CardTransferDTO();
        dto.setPurpose("Новая цель");

        when(cardTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(cardTransferRepository.save(entity)).thenReturn(entity);
        when(cardTransferMapper.mapToDTO(entity)).thenReturn(dto);

        cardTransferService.updateCardTransfer(id, dto);

        verify(cardTransferRepository).findById(id);
        verify(cardTransferRepository).save(entity);
        verify(cardTransferMapper).mapToDTO(entity);
    }

    @Test
    void deleteCardTransferTest() {
        Long id = 1L;
        CardTransfer entity = new CardTransfer();

        when(cardTransferRepository.findById(id)).thenReturn(Optional.of(entity));

        cardTransferService.deleteCardTransfer(id);

        verify(cardTransferRepository).findById(id);
        verify(cardTransferRepository).delete(entity);
    }

    @Test
    public void getById_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(cardTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cardTransferService.getById(id));
    }

    @Test
    public void updateCardTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        CardTransferDTO transferDTO = new CardTransferDTO();
        when(cardTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cardTransferService.updateCardTransfer(id, transferDTO));
    }

    @Test
    public void deleteCardTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(cardTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cardTransferService.deleteCardTransfer(id));
    }
}
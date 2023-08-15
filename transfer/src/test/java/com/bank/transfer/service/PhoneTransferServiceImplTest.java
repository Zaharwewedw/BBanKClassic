package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
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

public class PhoneTransferServiceImplTest {

    @Mock
    private PhoneTransferMapper phoneTransferMapper;

    @Mock
    private PhoneTransferRepository phoneTransferRepository;

    @InjectMocks
    private PhoneTransferServiceImpl phoneTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransferTest() {
        PhoneTransferDTO dto = new PhoneTransferDTO();
        PhoneTransfer entity = new PhoneTransfer();

        when(phoneTransferMapper.mapToEntity(dto)).thenReturn(entity);
        when(phoneTransferRepository.save(entity)).thenReturn(entity);
        when(phoneTransferMapper.mapToDTO(entity)).thenReturn(dto);

        phoneTransferService.createTransfer(dto);

        verify(phoneTransferMapper).mapToEntity(dto);
        verify(phoneTransferRepository).save(entity);
        verify(phoneTransferMapper).mapToDTO(entity);
    }

    @Test
    void getByIdTest() {
        Long id = 1L;
        PhoneTransfer entity = new PhoneTransfer();
        PhoneTransferDTO dto = new PhoneTransferDTO();

        when(phoneTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(phoneTransferMapper.mapToDTO(entity)).thenReturn(dto);

        phoneTransferService.getById(id);

        verify(phoneTransferRepository).findById(id);
        verify(phoneTransferMapper).mapToDTO(entity);
    }


    @Test
    void getAllTest() {
        List<PhoneTransfer> entities = Arrays.asList(new PhoneTransfer(), new PhoneTransfer());
        List<PhoneTransferDTO> dtos = Arrays.asList(new PhoneTransferDTO(), new PhoneTransferDTO());

        when(phoneTransferRepository.findAll()).thenReturn(entities);
        when(phoneTransferMapper.mapToDTO(any(PhoneTransfer.class))).thenReturn(dtos.get(0), dtos.get(1));

        phoneTransferService.getAll();

        verify(phoneTransferRepository).findAll();
        verify(phoneTransferMapper, times(2)).mapToDTO(any(PhoneTransfer.class));
    }

    @Test
    void updatePhoneTransferTest() {
        Long id = 1L;
        PhoneTransfer entity = new PhoneTransfer();
        PhoneTransferDTO dto = new PhoneTransferDTO();
        dto.setPurpose("Новая цель");

        when(phoneTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(phoneTransferRepository.save(entity)).thenReturn(entity);
        when(phoneTransferMapper.mapToDTO(entity)).thenReturn(dto);

        phoneTransferService.updatePhoneTransfer(id, dto);

        verify(phoneTransferRepository).findById(id);
        verify(phoneTransferRepository).save(entity);
        verify(phoneTransferMapper).mapToDTO(entity);
    }

    @Test
    void deletePhoneTransferTest() {
        Long id = 1L;
        PhoneTransfer entity = new PhoneTransfer();

        when(phoneTransferRepository.findById(id)).thenReturn(Optional.of(entity));

        phoneTransferService.deletePhoneTransfer(id);

        verify(phoneTransferRepository).findById(id);
        verify(phoneTransferRepository).delete(entity);
    }

    @Test
    public void getById_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(phoneTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> phoneTransferService.getById(id));
    }

    @Test
    public void updatePhoneTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        PhoneTransferDTO transferDTO = new PhoneTransferDTO();
        when(phoneTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> phoneTransferService.updatePhoneTransfer(id, transferDTO));
    }

    @Test
    public void deletePhoneTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(phoneTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> phoneTransferService.deletePhoneTransfer(id));
    }
}
package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountTransferServiceImplTest {

    @Mock
    private AccountTransferMapper accountTransferMapper;

    @Mock
    private AccountTransferRepository accountTransferRepository;

    @InjectMocks
    private AccountTransferServiceImpl accountTransferService;

    @Test
    void createTransfer() {
        AccountTransferDTO dto = new AccountTransferDTO();
        AccountTransfer entity = new AccountTransfer();

        when(accountTransferMapper.mapToEntity(dto)).thenReturn(entity);
        when(accountTransferRepository.save(entity)).thenReturn(entity);
        when(accountTransferMapper.mapToDTO(entity)).thenReturn(dto);

        accountTransferService.createTransfer(dto);

        verify(accountTransferMapper).mapToEntity(dto);
        verify(accountTransferRepository).save(entity);
        verify(accountTransferMapper).mapToDTO(entity);
    }

    @Test
    void getById() {
        Long id = 1L;
        AccountTransfer entity = new AccountTransfer();
        AccountTransferDTO dto = new AccountTransferDTO();

        when(accountTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(accountTransferMapper.mapToDTO(entity)).thenReturn(dto);

        accountTransferService.getById(id);

        verify(accountTransferRepository).findById(id);
        verify(accountTransferMapper).mapToDTO(entity);
    }

    @Test
    void getAll() {
        List<AccountTransfer> entities = Arrays.asList(new AccountTransfer(), new AccountTransfer());
        List<AccountTransferDTO> dtos = Arrays.asList(new AccountTransferDTO(), new AccountTransferDTO());

        when(accountTransferRepository.findAll()).thenReturn(entities);
        when(accountTransferMapper.mapToDTO(any(AccountTransfer.class))).thenReturn(dtos.get(0), dtos.get(1));

        accountTransferService.getAll();

        verify(accountTransferRepository).findAll();
        verify(accountTransferMapper, times(2)).mapToDTO(any(AccountTransfer.class));
    }

    @Test
    void updateAccountTransfer() {
        Long id = 1L;
        AccountTransfer entity = new AccountTransfer();
        AccountTransferDTO dto = new AccountTransferDTO();
        dto.setPurpose("Новая цель");

        when(accountTransferRepository.findById(id)).thenReturn(Optional.of(entity));
        when(accountTransferRepository.save(entity)).thenReturn(entity);
        when(accountTransferMapper.mapToDTO(entity)).thenReturn(dto);

        accountTransferService.updateAccountTransfer(id, dto);

        verify(accountTransferRepository).findById(id);
        verify(accountTransferRepository).save(entity);
        verify(accountTransferMapper).mapToDTO(entity);
    }

    @Test
    void deleteAccountTransfer() {
        Long id = 1L;
        AccountTransfer entity = new AccountTransfer();

        when(accountTransferRepository.findById(id)).thenReturn(Optional.of(entity));

        accountTransferService.deleteAccountTransfer(id);

        verify(accountTransferRepository).findById(id);
        verify(accountTransferRepository).delete(entity);
    }

    @Test
    public void getById_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(accountTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> accountTransferService.getById(id));
    }

    @Test
    public void updateAccountTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        AccountTransferDTO transferDTO = new AccountTransferDTO();
        when(accountTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> accountTransferService.updateAccountTransfer(id, transferDTO));
    }

    @Test
    public void deleteAccountTransfer_WhenTransferNotFound_ShouldThrowNotFoundException() {
        Long id = 1L;
        when(accountTransferRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> accountTransferService.deleteAccountTransfer(id));
    }
}
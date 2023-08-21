package com.bank.account.service;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.repository.AccountDetailsRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsImplTest {
    @Mock
    private AccountDetailsRepository accountDetailsRepository;
    @InjectMocks
    private AccountDetailsImpl accountDetailsImpl;

    @Test
    void shouldGetAllAccountDetails() {
        when(accountDetailsRepository.findAll()).thenReturn(testDb());
        List<AccountDetailsEntity> result = accountDetailsImpl.getAllAccountDetails();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldGetAccountDetailsById() {
        when(accountDetailsRepository.findById(1L)).thenReturn(Optional.ofNullable(testDb().get(0)));
        AccountDetailsEntity entity = accountDetailsImpl.getAccountDetailsById(1L);
        assertNotNull(entity);
        assertEquals(entity.getId(), testDb().get(0).getId());
    }

    @Test
    void shouldGetAccountDetailsByIdThrowException() {
        long l = 1L;
        when(accountDetailsRepository.findById(l)).thenReturn(Optional.empty());
        assertThrows(AccountDetailsNotFoundException.class, () -> {
            accountDetailsImpl.getAccountDetailsById(l);
        });
    }

    @Test
    void shouldAddAccountDetails() {
        AccountDetailsEntity entity = new AccountDetailsEntity(3L, 3L, 3L,3L,
                BigDecimal.valueOf(1.0), false, 3L);
        when(accountDetailsRepository.saveAndFlush(entity)).thenReturn(entity);
        AccountDetailsEntity result = accountDetailsImpl.addAccountDetails(entity);
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getAccountNumber(), result.getAccountNumber());
        assertEquals(entity.getBankDetailsId(), result.getBankDetailsId());
        assertEquals(entity.getProfileId(), result.getProfileId());
        assertEquals(entity.getMoney(), result.getMoney());
        assertEquals(entity.getPassportId(), result.getPassportId());
        assertEquals(entity.isNegativeBalance(), result.isNegativeBalance());
        verify(accountDetailsRepository, times(1)).saveAndFlush(entity);
    }

    @Test
    void shouldDeleteAccountDetails() {
        Long id = 3L;
        when(accountDetailsRepository.findById(id)).thenReturn(Optional.ofNullable(AccountDetailsEntity.builder()
                .id(id).build()));
        AccountDetailsEntity deletedAccount = accountDetailsImpl.deleteAccountDetails(id);
        assertEquals(id, deletedAccount.getId());
        verify(accountDetailsRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldUpdateAccountDetails() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        when(accountDetailsRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(accountDetailsRepository.save(entity)).thenReturn(entity);
        AccountDetailsEntity updatedEntity = accountDetailsImpl.updateAccountDetails(entity);
        verify(accountDetailsRepository, times(1)).findById(entity.getId());
        verify(accountDetailsRepository, times(1)).save(entity);
    }
    @Test
    void shouldUpdateAccountDetailsThrowException() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        when(accountDetailsRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountDetailsNotFoundException.class, () -> {
            accountDetailsImpl.updateAccountDetails(entity);
        });
    }



    private List<AccountDetailsEntity> testDb() {
        AccountDetailsEntity entity1 = new AccountDetailsEntity(1L, 1L, 1L, 1L,
                BigDecimal.valueOf(1.00), false, 1L);
        AccountDetailsEntity entity2 = new AccountDetailsEntity(2L, 2L, 2L, 2L,
                BigDecimal.valueOf(2.00), false, 2L);
        return List.of(entity1, entity2);
    }
}

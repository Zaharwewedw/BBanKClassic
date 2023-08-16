package com.bank.account.service;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.repository.AccountDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Mockito.when(accountDetailsRepository.findAll()).thenReturn(testDb());
        List<AccountDetailsEntity> result = accountDetailsImpl.getAllAccountDetails();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void shouldGetAccountDetailsById() {
        Mockito.when(accountDetailsRepository.findById(1L)).thenReturn(Optional.ofNullable(testDb().get(0)));
        AccountDetailsEntity entity = accountDetailsImpl.getAccountDetailsById(1L);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(entity.getId(), testDb().get(0).getId());
    }

    @Test
    void shouldGetAccountDetailsByIdThrowException() {
        long l = 1L;
        Mockito.when(accountDetailsRepository.findById(l)).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountDetailsNotFoundException.class, () -> {
            accountDetailsImpl.getAccountDetailsById(l);
        });
    }

    @Test
    void shouldAddAccountDetails() {
        AccountDetailsEntity entity = new AccountDetailsEntity(3L, 3L, 3L,3L,
                BigDecimal.valueOf(1.0), false, 3L);
        Mockito.when(accountDetailsRepository.saveAndFlush(entity)).thenReturn(entity);
        AccountDetailsEntity result = accountDetailsImpl.addAccountDetails(entity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(entity.getId(), result.getId());
        Assertions.assertEquals(entity.getAccountNumber(), result.getAccountNumber());
        Assertions.assertEquals(entity.getBankDetailsId(), result.getBankDetailsId());
        Assertions.assertEquals(entity.getProfileId(), result.getProfileId());
        Assertions.assertEquals(entity.getMoney(), result.getMoney());
        Assertions.assertEquals(entity.getPassportId(), result.getPassportId());
        Assertions.assertEquals(entity.isNegativeBalance(), result.isNegativeBalance());
        Mockito.verify(accountDetailsRepository, Mockito.times(1)).saveAndFlush(entity);
    }

    @Test
    void shouldDeleteAccountDetails() {
        Long id = 3L;
        Mockito.when(accountDetailsRepository.findById(id)).thenReturn(Optional.ofNullable(AccountDetailsEntity.builder()
                .id(id).build()));
        AccountDetailsEntity deletedAccount = accountDetailsImpl.deleteAccountDetails(id);
        System.out.println(deletedAccount);
        Assertions.assertEquals(id, deletedAccount.getId());
        Mockito.verify(accountDetailsRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldUpdateAccountDetails() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        Mockito.when(accountDetailsRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        Mockito.when(accountDetailsRepository.save(entity)).thenReturn(entity);
        AccountDetailsEntity updatedEntity = accountDetailsImpl.updateAccountDetails(entity);
        Mockito.verify(accountDetailsRepository, Mockito.times(1)).findById(entity.getId());
        Mockito.verify(accountDetailsRepository, Mockito.times(1)).save(entity);
    }
    @Test
    void shouldUpdateAccountDetailsThrowException() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        Mockito.when(accountDetailsRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountDetailsNotFoundException.class, () -> {
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

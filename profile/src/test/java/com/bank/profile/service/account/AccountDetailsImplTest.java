package com.bank.profile.service.account;

import com.bank.profile.entity.AccountDetails;
import com.bank.profile.exception.AccountDetailsNotFoundException;
import com.bank.profile.repo.AccountDetailsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountDetailsImplTest {

    @Mock
    private AccountDetailsRepo accountDetailsRepo;

    @InjectMocks
    private AccountDetailsImpl accountDetailService;

    @Test
    void getAll() {
        AccountDetails accountDetails1 = new AccountDetails();
        AccountDetails accountDetails2 = new AccountDetails();
        AccountDetails accountDetails3 = new AccountDetails();

        when(accountDetailsRepo.findAll()).thenReturn(List.of(
                accountDetails1,
                accountDetails2,
                accountDetails3
        ));

        assertEquals(3, accountDetailService.getAll().size());

    }

    @Test
    void getAccountDetailsById() {
        Long id = 1L;
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountId(id);

        when(accountDetailsRepo.findById(id)).thenReturn(Optional.of(accountDetails));
        assertEquals(1, accountDetailService.getAccountDetailsById(id).getAccountId());

    }

    @Test
    void getAccountDetailsById_NoAccountDetailsWithSuchId_throwsAccountDetailsNotFoundException() {
        assertThrows(AccountDetailsNotFoundException.class, () -> accountDetailService.getAccountDetailsById(15L));
    }

    @Test
    void add() {
        AccountDetails newAccountDetails = new AccountDetails();
        accountDetailService.add(newAccountDetails);
        verify(accountDetailsRepo, times(1)).save(newAccountDetails);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        AccountDetails deletedAccountDetails = new AccountDetails();
        when(accountDetailsRepo.findById(id)).thenReturn(Optional.of(deletedAccountDetails));
        accountDetailService.deleteById(id);
        verify(accountDetailsRepo, times(1)).findById(id);
        verify(accountDetailsRepo, times(1)).deleteById(id);
    }
}
package com.bank.profile.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDetailsTest {

    @Test
    @DisplayName("Test getters and setters for AccountDetails")
    void testGettersAndSettersMethods() {
        AccountDetails accountDetails = new AccountDetails();
        Profile profile = new Profile();

        profile.setId(1L);
        accountDetails.setAccountId(1L);
        accountDetails.setProfile(profile);

        assertAll("AccountDetails properties",
                () -> assertEquals(1L, accountDetails.getAccountId()),
                () -> assertEquals(1L, accountDetails.getProfile().getId())
        );
    }
}
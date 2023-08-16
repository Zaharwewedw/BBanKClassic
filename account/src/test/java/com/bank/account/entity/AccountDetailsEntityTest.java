package com.bank.account.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountDetailsEntityTest {

    @Test
    void setTest() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        entity.setId(1L);
        entity.setAccountNumber(1L);
        entity.setMoney(BigDecimal.valueOf(1.0));
        entity.setBankDetailsId(1L);
        entity.setPassportId(1L);
        entity.setNegativeBalance(false);
        entity.setProfileId(1L);
        Assertions.assertEquals(1L, entity.getId());
        Assertions.assertEquals(1L, entity.getAccountNumber());
        Assertions.assertEquals(BigDecimal.valueOf(1.0), entity.getMoney());
        Assertions.assertEquals(1L, entity.getBankDetailsId());
        Assertions.assertEquals(1L, entity.getPassportId());
        Assertions.assertEquals(false, entity.isNegativeBalance());
        Assertions.assertEquals(1L, entity.getProfileId());
    }

    @Test
    void builderTest() {
        long x = 1L;
        String testString = "AccountDetails{" +
                "id=1, " +
                "passportId=1, " +
                "accountNumber=1, " +
                "bankDetailsId=1, " +
                "money=1.0, " +
                "negativeBalance=false, " +
                "profileId=1}";
        AccountDetailsEntity newEntity = AccountDetailsEntity.builder()
                .id(x)
                .accountNumber(x)
                .money(BigDecimal.valueOf(1.0))
                .bankDetailsId(x)
                .negativeBalance(false)
                .profileId(x)
                .passportId(x)
                .build();
        String builderToString = AccountDetailsEntity.builder()
                .id(x)
                .accountNumber(x)
                .money(BigDecimal.valueOf(1.0))
                .bankDetailsId(x)
                .negativeBalance(false)
                .profileId(x)
                .passportId(x)
                .toString();
        AccountDetailsEntity entity = getSimpleEntity();
        Assertions.assertTrue(builderToString.contains("id=1"));
        Assertions.assertEquals(entity.getId(), newEntity.getId());
        Assertions.assertEquals(entity.getAccountNumber(), newEntity.getAccountNumber());
        Assertions.assertEquals(testString, newEntity.toString());
    }

    @Test
    void toStringTest() {
        String testString = "AccountDetails{" +
                "id=1, " +
                "passportId=1, " +
                "accountNumber=1, " +
                "bankDetailsId=1, " +
                "money=1.0, " +
                "negativeBalance=false, " +
                "profileId=1}";
        Assertions.assertEquals(testString, getSimpleEntity().toString());
    }

    private AccountDetailsEntity getSimpleEntity() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        entity.setId(1L);
        entity.setAccountNumber(1L);
        entity.setMoney(BigDecimal.valueOf(1.0));
        entity.setBankDetailsId(1L);
        entity.setPassportId(1L);
        entity.setNegativeBalance(false);
        entity.setProfileId(1L);
        return entity;
    }
}

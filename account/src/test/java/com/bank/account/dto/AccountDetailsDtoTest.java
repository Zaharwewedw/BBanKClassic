package com.bank.account.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountDetailsDtoTest {

    @Test
    void accountDetailsDtoTest() {
        long x = 1L;
        String dtoString = AccountDetailsDto.builder()
                .id(x)
                .accountNumber(x)
                .bankDetailsId(x)
                .negativeBalance(true)
                .money(BigDecimal.valueOf(1.0))
                .profileId(x)
                .passportId(x)
                .toString();
        String testString = "id=1";

        AccountDetailsDto dto = new AccountDetailsDto();
        dto.setId(x);
        dto.setAccountNumber(x);
        dto.setBankDetailsId(x);
        dto.setMoney(BigDecimal.valueOf(1.0));
        dto.setPassportId(x);
        dto.setNegativeBalance(true);
        dto.setProfileId(x);
        assertTrue(dtoString.contains(testString));
        assertEquals(x, dto.getBankDetailsId());
        assertEquals(x, dto.getId());
        assertEquals(x, dto.getAccountNumber());
        assertEquals(x, dto.getProfileId());
        assertEquals(x, dto.getPassportId());
        assertEquals(BigDecimal.valueOf(1.0), dto.getMoney());
        assertTrue(dto.isNegativeBalance());
    }
}

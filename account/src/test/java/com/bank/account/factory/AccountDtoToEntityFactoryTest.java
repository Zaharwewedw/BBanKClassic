package com.bank.account.factory;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountDtoToEntityFactoryTest {

    @Test
    void shouldMakeAccountDetailsEntityTest() {
        AccountDtoToEntityFactory factory = new AccountDtoToEntityFactory();
        long x = 1L;
        AccountDetailsDto dto = AccountDetailsDto.builder()
                .id(x)
                .accountNumber(x)
                .bankDetailsId(x)
                .passportId(x)
                .profileId(x)
                .money(BigDecimal.valueOf(1.0))
                .negativeBalance(true)
                .build();
        AccountDetailsEntity entity = factory.makeAccountDetailsEntity(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAccountNumber(), entity.getAccountNumber());
        assertEquals(dto.getBankDetailsId(), entity.getBankDetailsId());
        assertEquals(dto.getPassportId(), entity.getPassportId());
        assertEquals(dto.getProfileId(), entity.getProfileId());
        assertEquals(dto.getMoney(), entity.getMoney());
        assertEquals(dto.isNegativeBalance(), entity.isNegativeBalance());
    }

    @Test
    void shouldMakeAccountDetailsEntityListTest() {
        AccountDtoToEntityFactory factory = new AccountDtoToEntityFactory();
        List<AccountDetailsDto> list = List.of(new AccountDetailsDto(), new AccountDetailsDto());
        List<AccountDetailsEntity> dtoList = factory.makeAccountDetailsEntityList(list);
        assertTrue(dtoList.size()==2);
    }
}

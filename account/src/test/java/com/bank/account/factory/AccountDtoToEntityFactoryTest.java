package com.bank.account.factory;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getAccountNumber(), entity.getAccountNumber());
        Assertions.assertEquals(dto.getBankDetailsId(), entity.getBankDetailsId());
        Assertions.assertEquals(dto.getPassportId(), entity.getPassportId());
        Assertions.assertEquals(dto.getProfileId(), entity.getProfileId());
        Assertions.assertEquals(dto.getMoney(), entity.getMoney());
        Assertions.assertEquals(dto.isNegativeBalance(), entity.isNegativeBalance());
    }

    @Test
    void shouldMakeAccountDetailsEntityListTest() {
        AccountDtoToEntityFactory factory = new AccountDtoToEntityFactory();
        List<AccountDetailsDto> list = List.of(new AccountDetailsDto(), new AccountDetailsDto());
        List<AccountDetailsEntity> dtoList = factory.makeAccountDetailsEntityList(list);
        Assertions.assertTrue(dtoList.size()==2);
    }
}

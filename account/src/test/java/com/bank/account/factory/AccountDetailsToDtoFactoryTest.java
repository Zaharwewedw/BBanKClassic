package com.bank.account.factory;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AccountDetailsToDtoFactoryTest {
    @Test
    void shouldMakeAccountDetailsDtoListTest() {
        AccountDetailsToDtoFactory factory = new AccountDetailsToDtoFactory();
        List<AccountDetailsEntity> list = List.of(new AccountDetailsEntity(), new AccountDetailsEntity());
        List<AccountDetailsDto> dtoList = factory.makeAccountDetailsDtoList(list);
        assertTrue(dtoList.size()==2);
    }
}

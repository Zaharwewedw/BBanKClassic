package com.bank.account.factory;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDtoToEntityFactory {

    public AccountDetailsEntity makeAccountDetailsEntity(AccountDetailsDto accountDetailsDto) {
        return AccountDetailsEntity.builder()
                .id(accountDetailsDto.getId())
                .passportId(accountDetailsDto.getPassportId())
                .accountNumber(accountDetailsDto.getAccountNumber())
                .bankDetailsId(accountDetailsDto.getBankDetailsId())
                .money(accountDetailsDto.getMoney())
                .negativeBalance(accountDetailsDto.isNegativeBalance())
                .profileId(accountDetailsDto.getProfileId())
                .build();
    }

    public List<AccountDetailsEntity> makeAccountDetailsEntityList(List<AccountDetailsDto> accountDetailsDtoList) {
        List<AccountDetailsEntity> result = new ArrayList<>();
        for(AccountDetailsDto dto: accountDetailsDtoList) result.add(makeAccountDetailsEntity(dto));
        return result;
    }
}

package com.bank.account.factory;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDetailsToDtoFactory {

    public AccountDetailsDto makeAccountDetailsDto(AccountDetailsEntity accountDetailsEntity) {
        return AccountDetailsDto.builder()
                .id(accountDetailsEntity.getId())
                .passportId(accountDetailsEntity.getPassportId())
                .accountNumber(accountDetailsEntity.getAccountNumber())
                .bankDetailsId(accountDetailsEntity.getBankDetailsId())
                .money(accountDetailsEntity.getMoney())
                .negativeBalance(accountDetailsEntity.isNegativeBalance())
                .profileId(accountDetailsEntity.getProfileId())
                .build();
    }

    public List<AccountDetailsDto> makeAccountDetailsDtoList(List<AccountDetailsEntity> accountDetailsEntities) {
        List<AccountDetailsDto> result = new ArrayList<>();
        for(AccountDetailsEntity entity: accountDetailsEntities) result.add(makeAccountDetailsDto(entity));
        return result;
    }
}

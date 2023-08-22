package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.exception.BadRequestException;
import com.bank.account.factory.AccountDetailsToDtoFactory;
import com.bank.account.factory.AccountDtoToEntityFactory;
import com.bank.account.service.AccountDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsControllerTest {

    @Mock
    private AccountDetailsService accountDetailsService;

    @Mock
    private AccountDetailsToDtoFactory entityToDtoFactory;

    @Mock
    private AccountDtoToEntityFactory dtoToEntityFactory;

    @InjectMocks
    private AccountDetailsController accountDetailsController;
    @Test
    void shouldGetAllAccountDetailsTest() {
        AccountDetailsEntity entity1 = AccountDetailsEntity.builder().id(1L).build();
        AccountDetailsEntity entity2 = AccountDetailsEntity.builder().id(2L).build();
        AccountDetailsDto entityDto1 = AccountDetailsDto.builder().id(1L).build();
        AccountDetailsDto entityDto2 = AccountDetailsDto.builder().id(2L).build();
        when(accountDetailsService.getAllAccountDetails()).thenReturn(List.of(entity1, entity2));
        when(entityToDtoFactory.makeAccountDetailsDtoList(List.of(entity1, entity2))).thenReturn(List.of(entityDto1, entityDto2));
        ResponseEntity<List<AccountDetailsDto>> list = accountDetailsController.getAllAccountDetails();
        Assertions.assertEquals(200, list.getStatusCodeValue());
        Assertions.assertEquals(2, list.getBody().size());
    }

    @Test
    void shouldGetAccountDetailsByIdTest() {
        long id = 1L;
        AccountDetailsEntity entity = getEntity();
        when(accountDetailsService.getAccountDetailsById(id)).thenReturn(entity);
        when(entityToDtoFactory.makeAccountDetailsDto(entity)).thenReturn(getDto(entity));
        ResponseEntity<AccountDetailsDto> dto = accountDetailsController.getAccountDetailsById(id);
        Assertions.assertEquals(id, dto.getBody().getId());
        Assertions.assertEquals(200, dto.getStatusCodeValue());
        verify(accountDetailsService, times(1)).getAccountDetailsById(id);
    }

    @Test
    void shouldThrowBadRequestExceptionOnGetAccountDetailsById() {
        long id = 10L;
        when(accountDetailsService.getAccountDetailsById(id)).thenThrow(BadRequestException.class);
        Assertions.assertThrows(BadRequestException.class, () -> accountDetailsController.getAccountDetailsById(id));
    }

    @Test
    void shouldAddNewAccountDetailsTest() {
        AccountDetailsEntity entity = new AccountDetailsEntity();
        AccountDetailsDto dto = getDto(entity);
        when(dtoToEntityFactory.makeAccountDetailsEntity(dto)).thenReturn(entity);
        when(accountDetailsService.addAccountDetails(entity)).thenReturn(entity);
        ResponseEntity<AccountDetailsDto> result = accountDetailsController.addNewAccountDetails(dto);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        verify(accountDetailsService, times(1)).addAccountDetails(entity);
    }

    @Test
    void shouldDeleteAccountDetailsTest() {
        long id = 1L;
        AccountDetailsEntity entity = getEntity();
        AccountDetailsDto dto = getDto(entity);
        when(accountDetailsService.deleteAccountDetails(id)).thenReturn(entity);
        when(entityToDtoFactory.makeAccountDetailsDto(entity)).thenReturn(dto);
        ResponseEntity<AccountDetailsDto> deletedDto = accountDetailsController.deleteEntity(id);
        Assertions.assertEquals(dto.getId(), deletedDto.getBody().getId());
        verify(accountDetailsService, times(1)).deleteAccountDetails(id);
    }

    @Test
    void shouldEditAccountDetails() {
        AccountDetailsEntity entity = getEntity();
        AccountDetailsDto dto = getDto(entity);
        AccountDetailsEntity updatedEntity = getEntity();
        when(dtoToEntityFactory.makeAccountDetailsEntity(dto)).thenReturn(entity);
        when(accountDetailsService.updateAccountDetails(entity)).thenReturn(updatedEntity);
        AccountDetailsDto updatedDto = accountDetailsController.editAccountDetails(dto);
        verify(accountDetailsService, times(1)).updateAccountDetails(entity);
        verify(dtoToEntityFactory, times(1)).makeAccountDetailsEntity(dto);
    }

    private AccountDetailsEntity getEntity() {
        long id = 1L;
        AccountDetailsEntity entity = AccountDetailsEntity.builder()
                .id(id)
                .accountNumber(id)
                .money(BigDecimal.valueOf(1.0))
                .bankDetailsId(id)
                .negativeBalance(false)
                .profileId(id)
                .passportId(id)
                .build();
        return entity;
    }

    private AccountDetailsDto getDto(AccountDetailsEntity entity) {
        AccountDetailsToDtoFactory factory1 = new AccountDetailsToDtoFactory();
        return factory1.makeAccountDetailsDto(entity);
    }
}

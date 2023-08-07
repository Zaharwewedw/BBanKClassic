package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.factory.AccountDetailsToDtoFactory;
import com.bank.account.factory.AccountDtoToEntityFactory;
import com.bank.account.service.AccountDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
@Tag(name = "AccountDetails Controller")
public class AccountDetailsController {

    @Autowired
    AccountDetailsService service;

    @Autowired
    AccountDetailsToDtoFactory accountDetailsToDtoFactory;

    @Autowired
    AccountDtoToEntityFactory accountDtoToEntityFactory;

    @GetMapping()
    @Operation(summary = "Get list of all AccountDetails", description = "Get list of all AccountDetails")
    public ResponseEntity<List<AccountDetailsDto>> getAllAccountDetails() {
        List<AccountDetailsDto> result = accountDetailsToDtoFactory.makeAccountDetailsDtoList(service.getAllAccountDetails());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Add new AccountDetails", description = "Add new AccountDetails")
    public ResponseEntity<AccountDetailsDto> addNewAccountDetails(@Valid @RequestBody AccountDetailsDto accountDetailsDto) {
        AccountDetailsEntity entity = accountDtoToEntityFactory.makeAccountDetailsEntity(accountDetailsDto);
        AccountDetailsEntity createdEntity = service.addAccountDetails(entity);
        return new ResponseEntity<>(accountDetailsToDtoFactory.makeAccountDetailsDto(createdEntity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get AccountDetails by id", description = "Get AccountDetails by id")
    public ResponseEntity<AccountDetailsDto> getAccountDetailsById(@PathVariable long id) {
        AccountDetailsEntity entity = service.getAccountDetailsById(id);
        return new ResponseEntity<>(accountDetailsToDtoFactory.makeAccountDetailsDto(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete AccountDetails by id", description = "Delete AccountDetails by id")
    public ResponseEntity<AccountDetailsDto> deleteEntity(@PathVariable long id) {
        return new ResponseEntity<>(accountDetailsToDtoFactory.makeAccountDetailsDto(service.deleteAccountDetails(id)), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "Edit AccountDetails")
    public AccountDetailsDto editAccountDetails(@RequestBody AccountDetailsDto accountDetailsDto){
        return accountDetailsToDtoFactory.makeAccountDetailsDto(service.updateAccountDetails(accountDtoToEntityFactory.makeAccountDetailsEntity(accountDetailsDto)));
    }


}

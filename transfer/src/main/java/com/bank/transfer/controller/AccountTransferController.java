package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;
import java.util.List;

//Рест контроллер для AccountTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@Api(value = "Account Transfer Management")
@RestController
@RequestMapping("/api/transfer")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;

    @Autowired
    public AccountTransferController(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @ApiOperation(value = "Create a new transfer")
    @PostMapping("/accountTransfer")
    public ResponseEntity<?> createTransfer(@Valid @RequestBody AccountTransferDTO accountTransferDTO) throws InsufficientResourcesException {
        return new ResponseEntity<>(accountTransferService.createTransfer(accountTransferDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a transfer by its ID")
    @GetMapping("/accountTransfer/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if(accountTransferService.getById(id) == null) {
            throw new NoSuchTransferException("There is no transfer with ID " + id + "in Database");
        }
        return new ResponseEntity<>(accountTransferService.getById(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Get all transfers")
    @GetMapping("/accountTransfer")
    public ResponseEntity<?> findAll() {
        List<AccountTransferDTO> accountTransferDTOList = accountTransferService.getAll();
        return ResponseEntity.ok(accountTransferDTOList);

    }

    @ApiOperation(value = "Update a transfer's purpose")
    @PatchMapping("/accountTransfer/{id}")
    public AccountTransferDTO updateAccountTransfer(@PathVariable Long id,@Valid @RequestBody AccountTransferDTO accountTransferDTO) {
        return accountTransferService.updateAccountTransfer(id, accountTransferDTO);
    }

    @ApiOperation(value = "Delete a transfer by its ID")
    @DeleteMapping("/accountTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountTransfer(@PathVariable Long id) {
        accountTransferService.deleteAccountTransfer(id);
    }
}

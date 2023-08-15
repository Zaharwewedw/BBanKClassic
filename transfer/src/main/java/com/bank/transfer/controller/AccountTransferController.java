package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;
import java.util.List;

//Рест контроллер для AccountTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@Tag(name = "Account Transfer Management")
@RestController
@RequestMapping("/api/transfer")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;

    @Autowired
    public AccountTransferController(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @Operation(summary = "Create a new transfer")
    @PostMapping("/accountTransfer")
    public ResponseEntity<?> createTransfer(@Valid @RequestBody AccountTransferDTO accountTransferDTO) throws InsufficientResourcesException {
        return new ResponseEntity<>(accountTransferService.createTransfer(accountTransferDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a transfer by its ID")
    @GetMapping("/accountTransfer/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AccountTransferDTO transfer = accountTransferService.getById(id);
        if(transfer == null) {
            throw new NoSuchTransferException("There is no transfer with this ID");
        }
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }

    @Operation(summary = "Get all transfers")
    @GetMapping("/accountTransfer")
    public ResponseEntity<?> findAll() {
        List<AccountTransferDTO> accountTransferDTOList = accountTransferService.getAll();
        return ResponseEntity.ok(accountTransferDTOList);

    }

    @Operation(summary = "Update a transfer's purpose")
    @PatchMapping("/accountTransfer/{id}")
    public AccountTransferDTO updateAccountTransfer(@PathVariable Long id,@Valid @RequestBody AccountTransferDTO accountTransferDTO) {
        return accountTransferService.updateAccountTransfer(id, accountTransferDTO);
    }

    @Operation(summary = "Delete a transfer by its ID")
    @DeleteMapping("/accountTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountTransfer(@PathVariable Long id) {
        accountTransferService.deleteAccountTransfer(id);
    }
}

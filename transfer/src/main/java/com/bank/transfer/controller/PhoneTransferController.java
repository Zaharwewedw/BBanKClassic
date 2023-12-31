package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.PhoneTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;
import java.util.List;

//Рест контроллер для PhoneTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@RestController
@RequestMapping("/api/transfer")
@Tag(name = "PhoneTransfer Management System")
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;

    @Autowired
    public PhoneTransferController(PhoneTransferService phoneTransferService) {
        this.phoneTransferService = phoneTransferService;
    }

    @Operation(summary = "Create a new phone transfer")
    @PostMapping("/phoneTransfer")
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneTransferDTO createPhoneTransfer(@Valid @RequestBody PhoneTransferDTO phoneTransferDTO) throws InsufficientResourcesException {
        return phoneTransferService.createTransfer(phoneTransferDTO);
    }

    @Operation(summary = "Get a transfer by its ID")
    @GetMapping("/phoneTransfer/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PhoneTransferDTO transfer = phoneTransferService.getById(id);
        if(transfer == null) {
            throw new NoSuchTransferException("There is no transfer with this ID");
        }
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }

    @Operation(summary = "View a list of available phone transfers")
    @GetMapping("/phoneTransfer")
    public List<PhoneTransferDTO> getAllPhoneTransfers() {
        return phoneTransferService.getAll();
    }

    @Operation(summary = "Update a phone transfer")
    @PatchMapping("/phoneTransfer/{id}")
    public PhoneTransferDTO updatePhoneTransfer(@PathVariable Long id, @Valid @RequestBody PhoneTransferDTO phoneTransferDTO) {
        return phoneTransferService.updatePhoneTransfer(id, phoneTransferDTO);
    }

    @Operation(summary = "Delete a phone transfer")
    @DeleteMapping("/phoneTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoneTransfer(@PathVariable Long id) {
        phoneTransferService.deletePhoneTransfer(id);
    }
}

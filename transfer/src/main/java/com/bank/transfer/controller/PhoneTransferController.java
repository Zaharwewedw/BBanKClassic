package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.PhoneTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;
import java.util.List;

//Рест контроллер для PhoneTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@RestController
@RequestMapping("/api/transfer")
@Api(value = "PhoneTransfer Management System")
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;

    @Autowired
    public PhoneTransferController(PhoneTransferService phoneTransferService) {
        this.phoneTransferService = phoneTransferService;
    }

    @ApiOperation(value = "Create a new phone transfer", response = PhoneTransferDTO.class)
    @PostMapping("/phoneTransfer")
    public PhoneTransferDTO createPhoneTransfer(@Valid @RequestBody PhoneTransferDTO phoneTransferDTO) throws InsufficientResourcesException {
        return phoneTransferService.createTransfer(phoneTransferDTO);
    }

    @ApiOperation(value = "Get a phone transfer by Id", response = PhoneTransferDTO.class)
    @GetMapping("/phoneTransfer/{id}")
    public PhoneTransferDTO getPhoneTransferById(@PathVariable Long id) {
        if (phoneTransferService.getById(id) == null) {
            throw new NoSuchTransferException("There is no transfer with ID " + id + "in Database");
        }
        return phoneTransferService.getById(id);
    }

    @ApiOperation(value = "View a list of available phone transfers", response = List.class)
    @GetMapping("/phoneTransfer")
    public List<PhoneTransferDTO> getAllPhoneTransfers() {
        return phoneTransferService.getAll();
    }

    @ApiOperation(value = "Update a phone transfer", response = PhoneTransferDTO.class)
    @PatchMapping("/phoneTransfer/{id}")
    public PhoneTransferDTO updatePhoneTransfer(@PathVariable Long id, @Valid @RequestBody PhoneTransferDTO phoneTransferDTO) {
        return phoneTransferService.updatePhoneTransfer(id, phoneTransferDTO);
    }

    @ApiOperation(value = "Delete a phone transfer")
    @DeleteMapping("/phoneTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoneTransfer(@PathVariable Long id) {
        phoneTransferService.deletePhoneTransfer(id);
    }
}

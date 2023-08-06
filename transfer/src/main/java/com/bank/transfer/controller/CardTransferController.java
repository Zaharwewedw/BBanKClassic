package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.CardTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;
import java.util.List;

//Рест контроллер для CardTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@RestController
@RequestMapping("/api/transfer")
@Api(value = "CardTransfer Management System")
public class CardTransferController {

    private final CardTransferService cardTransferService;

    @Autowired
    public CardTransferController(CardTransferService cardTransferService) {
        this.cardTransferService = cardTransferService;
    }

    @ApiOperation(value = "Create a new card transfer", response = CardTransferDTO.class)
    @PostMapping("/cardTransfer")
    public CardTransferDTO createCardTransfer(@Valid @RequestBody CardTransferDTO cardTransferDTO) throws InsufficientResourcesException {
        return cardTransferService.createTransfer(cardTransferDTO);
    }

    @ApiOperation(value = "Get a card transfer by Id", response = CardTransferDTO.class)
    @GetMapping("/cardTransfer/{id}")
    public CardTransferDTO getCardTransferById(@PathVariable Long id) {
        if (cardTransferService.getById(id) == null) {
            throw new NoSuchTransferException("There is no transfer with ID " + id + "in Database");
        }
        return cardTransferService.getById(id);
    }

    @ApiOperation(value = "View a list of available card transfers", response = List.class)
    @GetMapping("/cardTransfer")
    public ResponseEntity<?> findAll() {
        List<CardTransferDTO> cardTransferDTOList = cardTransferService.getAll();
        return ResponseEntity.ok(cardTransferDTOList);
    }

    @ApiOperation(value = "Update a card transfer", response = CardTransferDTO.class)
    @PatchMapping("/cardTransfer/{id}")
    public CardTransferDTO updateCardTransfer(@PathVariable Long id, @Valid @RequestBody CardTransferDTO cardTransferDTO) {
        return cardTransferService.updateCardTransfer(id, cardTransferDTO);
    }

    @ApiOperation(value = "Delete a card transfer")
    @DeleteMapping("/cardTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardTransfer(@PathVariable Long id) {
        cardTransferService.deleteCardTransfer(id);
    }
}
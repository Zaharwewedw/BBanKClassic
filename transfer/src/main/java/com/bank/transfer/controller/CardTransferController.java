package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.CardTransferService;
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

//Рест контроллер для CardTransfer с аннотациями Сваггера, валидацией и c GET, POST, PATCH, DELETE методами
@RestController
@RequestMapping("/api/transfer")
@Tag(name = "CardTransfer Management System")
public class CardTransferController {

    private final CardTransferService cardTransferService;

    @Autowired
    public CardTransferController(CardTransferService cardTransferService) {
        this.cardTransferService = cardTransferService;
    }

    @Operation(summary = "Create a new card transfer")
    @PostMapping("/cardTransfer")
    @ResponseStatus(HttpStatus.CREATED)
    public CardTransferDTO createCardTransfer(@Valid @RequestBody CardTransferDTO cardTransferDTO) throws InsufficientResourcesException {
        return cardTransferService.createTransfer(cardTransferDTO);
    }

    @Operation(summary = "Get a transfer by its ID")
    @GetMapping("/cardTransfer/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CardTransferDTO transfer = cardTransferService.getById(id);
        if(transfer == null) {
            throw new NoSuchTransferException("There is no transfer with this ID");
        }
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }

    @Operation(summary = "View a list of available card transfers")
    @GetMapping("/cardTransfer")
    public ResponseEntity<?> findAll() {
        List<CardTransferDTO> cardTransferDTOList = cardTransferService.getAll();
        return ResponseEntity.ok(cardTransferDTOList);
    }

    @Operation(summary = "Update a card transfer")
    @PatchMapping("/cardTransfer/{id}")
    public CardTransferDTO updateCardTransfer(@PathVariable Long id, @Valid @RequestBody CardTransferDTO cardTransferDTO) {
        return cardTransferService.updateCardTransfer(id, cardTransferDTO);
    }

    @Operation(summary = "Delete a card transfer")
    @DeleteMapping("/cardTransfer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardTransfer(@PathVariable Long id) {
        cardTransferService.deleteCardTransfer(id);
    }
}
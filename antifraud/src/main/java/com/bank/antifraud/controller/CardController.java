package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudSuspiciousAccountTransferDTO;
import com.bank.antifraud.dto.AntifraudSuspiciousCardTransferDTO;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudCardNotFoundException;
import com.bank.antifraud.service.CardTransferService;
import com.bank.antifraud.util.mapper.CardMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/card")
public class CardController {

    public final CardTransferService cardTransferService;

    @Autowired
    public CardController(CardTransferService cardTransferService) {
        this.cardTransferService = cardTransferService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get specific card anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousCardTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public ResponseEntity<AntifraudSuspiciousCardTransferDTO> getcard(@PathVariable long id) {
        return ResponseEntity.ok(CardMapper.INSTANCE.cardToCardDTO(cardTransferService.findById(id)
                .orElseThrow(() -> new AntifraudCardNotFoundException("no record with this id"))));
    }

    @GetMapping
    @Operation(summary = "get list of all card anti-fraud operations")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the records",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AntifraudSuspiciousCardTransferDTO.class)
                    ))}),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public ResponseEntity<List<AntifraudSuspiciousCardTransferDTO>> getAll() {
        return ResponseEntity.ok(cardTransferService.getAll().stream()
                .map(e->CardMapper.INSTANCE.cardToCardDTO(e)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete specific card anti-fraud data")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "success delete", content = @Content
            ),
            @ApiResponse(
                    responseCode = "400", description = "record not found (not done yet, in process)"
                    , content = @Content
            )
    })
    public void delete(@PathVariable long id) {
        cardTransferService.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "add card anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "successful record", content = @Content
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid format (not done yet, in process)",
                    content = @Content
            )
    })
    public void save(@RequestBody AntifraudSuspiciousCardTransferDTO dto) {
        cardTransferService.save(CardMapper.INSTANCE.cardDTOToCard(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update specific card anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "success update of the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousCardTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public void update(@PathVariable long id, @RequestBody AntifraudSuspiciousCardTransferDTO dto) {
        cardTransferService.update(id, CardMapper.INSTANCE.cardDTOToCard(dto));
    }
}

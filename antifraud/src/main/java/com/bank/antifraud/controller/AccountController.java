package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudSuspiciousAccountTransferDTO;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudCardNotFoundException;
import com.bank.antifraud.service.AccountTransferService;
import com.bank.antifraud.util.mapper.AccountMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountTransferService accountTransferService;

    @Autowired
    public AccountController(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get specific account anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousAccountTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public ResponseEntity<AntifraudSuspiciousAccountTransferDTO> getAccount(@PathVariable long id) {
        return ResponseEntity.ok(AccountMapper.INSTANCE
                .accountToAccountDTO(accountTransferService.findById(id)
                        .orElseThrow(() -> new AntifraudAccountNotFoundException("no record with this id"))));
    }

    @GetMapping
    @Operation(summary = "get list of all account anti-fraud operations")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the records",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AntifraudSuspiciousAccountTransferDTO.class)
                    ))})
    })
    public ResponseEntity<List<AntifraudSuspiciousAccountTransferDTO>> getAll() {
        return ResponseEntity.ok(accountTransferService.getAll().stream()
                .map(e -> AccountMapper.INSTANCE.accountToAccountDTO(e)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete specific account anti-fraud data")
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
        accountTransferService.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "add account anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "successful record", content = @Content
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid format (not done yet, in process)",
                    content = @Content
            )
    })
    public void save(@RequestBody AntifraudSuspiciousAccountTransferDTO dto) {
        accountTransferService.save(AccountMapper.INSTANCE.accountDTOToAccount(dto));
    }

    @PutMapping("{id}")
    @Operation(summary = "change specific account anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "success change the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousAccountTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public void update(@PathVariable long id, @RequestBody AntifraudSuspiciousAccountTransferDTO dto) {
        accountTransferService.update(id, AccountMapper.INSTANCE.accountDTOToAccount(dto));
    }
}

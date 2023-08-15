package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudSuspiciousAccountTransferDTO;
import com.bank.antifraud.dto.AntifraudSuspiciousPhoneTransferDTO;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.exception.AntifraudPhoneNotFoundException;
import com.bank.antifraud.service.PhoneTransferService;
import com.bank.antifraud.util.mapper.PhoneMapper;
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
@RequestMapping("/phone")
public class PhoneController {

    public final PhoneTransferService phoneTransferService;

    @Autowired
    public PhoneController(PhoneTransferService phoneTransferService) {
        this.phoneTransferService = phoneTransferService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get specific phone anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousPhoneTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public ResponseEntity<AntifraudSuspiciousPhoneTransferDTO> getPhone(@PathVariable long id) {
        return ResponseEntity.ok(PhoneMapper.INSTANCE.phoneToPhoneDTO(phoneTransferService.findById(id)
                .orElseThrow(() -> new AntifraudPhoneNotFoundException("no record with this id"))));
    }

    @GetMapping
    @Operation(summary = "get list of all phone anti-fraud operations")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the records",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = AntifraudSuspiciousPhoneTransferDTO.class)
                    ))}),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public ResponseEntity<List<AntifraudSuspiciousPhoneTransferDTO>> getAll() {
        return ResponseEntity.ok(phoneTransferService.getAll().stream()
                .map(e -> PhoneMapper.INSTANCE.phoneToPhoneDTO(e)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete specific phone anti-fraud data")
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
        phoneTransferService.deleteById(id);
    }

    @PostMapping
    @Operation(summary = "add phone anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "successful record", content = @Content
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid format (not done yet, in process)",
                    content = @Content
            )
    })
    public void save(@RequestBody AntifraudSuspiciousPhoneTransferDTO dto) {
        phoneTransferService.save(PhoneMapper.INSTANCE.phoneDTOToPhone(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "get specific phone anti-fraud record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "found the record",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AntifraudSuspiciousPhoneTransferDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "invalid id",
                    content = @Content
            )
    })
    public void update(@PathVariable long id, @RequestBody AntifraudSuspiciousPhoneTransferDTO dto) {
        phoneTransferService.update(id, PhoneMapper.INSTANCE.phoneDTOToPhone(dto));
    }
}
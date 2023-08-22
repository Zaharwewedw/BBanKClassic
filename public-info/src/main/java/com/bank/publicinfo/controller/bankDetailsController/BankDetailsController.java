package com.bank.publicinfo.controller.bankDetailsController;

import com.bank.publicinfo.dto.bankDetailsDto.BankDetailsDto;
import com.bank.publicinfo.exception.bankDetailsException.BankDetailsNotSaveException;
import com.bank.publicinfo.mapper.bankDetailsMapper.BankDetailsMapper;
import com.bank.publicinfo.service.bankDetailsService.BankDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "BankDetails", description = "BankDetails Management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping
public class BankDetailsController {
    final BankDetailsService bankDetailsService;
    final BankDetailsMapper bankDetailsMapper;

    @Operation(summary = "Get all bankDetails from the database")
    @GetMapping
    public ResponseEntity<?> getAllBankDetails() {
        return ResponseEntity.ok(bankDetailsMapper.toDtoList(bankDetailsService.getAll()));
    }

    @Operation(summary = "Get bank details from the database by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBankDetails(@PathVariable Long id) {
        return ResponseEntity.ok(bankDetailsMapper.toDto(bankDetailsService.getById(id)));
    }

    @Operation(summary = "Save bankDetails to the database")
    @PostMapping
    public ResponseEntity<?> saveBankDetails(@RequestBody @Validated BankDetailsDto bankDetailsDto
            , BindingResult bindingResult) throws BankDetailsNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new BankDetailsNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(bankDetailsService.save(bankDetailsMapper.toEntity(bankDetailsDto)));
    }

    @Operation(summary = "Update bankDetails to the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBankDetails(@PathVariable Long id
            , @RequestBody @Validated BankDetailsDto bankDetailsDto
            , BindingResult bindingResult) throws BankDetailsNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new BankDetailsNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(bankDetailsService.update(id, bankDetailsMapper.toEntity(bankDetailsDto)));
    }

    @Operation(summary = "Delete bankDetails from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankDetails(@PathVariable Long id) {
        return ResponseEntity.ok(bankDetailsMapper.toDto(bankDetailsService.delete(id)));
    }
}

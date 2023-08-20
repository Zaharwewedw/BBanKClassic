package com.bank.publicinfo.controller.bankDetailsController;

import com.bank.publicinfo.dto.bankDetailsDto.LicenseDto;
import com.bank.publicinfo.exception.bankDetailsException.LicenseNotSaveException;
import com.bank.publicinfo.mapper.bankDetailsMapper.LicenseMapper;
import com.bank.publicinfo.service.bankDetailsService.LicenseService;
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

@Tag(name = "Licenses", description = "Licenses Management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/{id}/license")
public class LicenseController {

    final LicenseService licenseService;
    final LicenseMapper licenseMapper;

    @Operation(summary = "Get all licenses of bankDetails from the database")
    @GetMapping()
    public ResponseEntity<?> getAllLicense(@PathVariable("id") Long id) {
        return ResponseEntity.ok(licenseMapper.toDtoList(licenseService.getAll(id)));
    }

    @Operation(summary = "Get license from the database by ID")
    @GetMapping("/{idLicense}")
    public ResponseEntity<?> getLicense(@PathVariable("idLicense") Long idLicense) {
        return ResponseEntity.ok(licenseMapper.toDto(licenseService.getById(idLicense)));
    }

    @Operation(summary = "Save license to the database")
    @PostMapping
    public ResponseEntity<?> saveLicense(@PathVariable("id") Long id, @RequestBody @Validated LicenseDto licenseDto
            , BindingResult bindingResult) throws LicenseNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new LicenseNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(licenseService.save(id, licenseMapper.toEntity(licenseDto)));
    }

    @Operation(summary = "Update license to the database")
    @PutMapping("/{idLicense}")
    public ResponseEntity<?> updateLicense(@PathVariable("idLicense") Long idLicense
            , @RequestBody @Validated LicenseDto licenseDto, BindingResult bindingResult)
            throws LicenseNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new LicenseNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(licenseService.update(idLicense, licenseMapper.toEntity(licenseDto)));
    }

    @Operation(summary = "Delete license from the database")
    @DeleteMapping("/{idLicense}")
    public ResponseEntity<?> deleteLicense(@PathVariable("idLicense") Long idLicense) {
        return ResponseEntity.ok(licenseMapper.toDto(licenseService.delete(idLicense)));
    }
}

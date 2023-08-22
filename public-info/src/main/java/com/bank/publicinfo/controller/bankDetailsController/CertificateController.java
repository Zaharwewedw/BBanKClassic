package com.bank.publicinfo.controller.bankDetailsController;

import com.bank.publicinfo.dto.bankDetailsDto.CertificateDto;
import com.bank.publicinfo.exception.bankDetailsException.CertificateNotSaveException;
import com.bank.publicinfo.mapper.bankDetailsMapper.CertificateMapper;
import com.bank.publicinfo.service.bankDetailsService.CertificateService;
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
@Tag(name = "Certificates", description = "Certificates Management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/{id}/certificate")
public class CertificateController {
    final CertificateService certificateService;
    final CertificateMapper certificateMapper;
    @Operation(summary = "Get all certificates of bankDetails from the database")
    @GetMapping()
    public ResponseEntity<?> getAllCertificate(@PathVariable("id") Long id) {
        return ResponseEntity.ok(certificateMapper.toDtoList(certificateService.getAll(id)));
    }
    @Operation(summary = "Get certificate from the database by ID")
    @GetMapping("/{idCertificate}")
    public ResponseEntity<?> getCertificate(@PathVariable("idCertificate") Long idCertificate) {
        return ResponseEntity.ok(certificateMapper.toDto(certificateService.getById(idCertificate)));
    }
    @Operation(summary = "Save certificate to the database")
    @PostMapping
    public ResponseEntity<?> saveCertificate(@PathVariable("id") Long id, @RequestBody @Validated CertificateDto certificateDto
            , BindingResult bindingResult) throws CertificateNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new CertificateNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(certificateService.save(id, certificateMapper.toEntity(certificateDto)));
    }
    @Operation(summary = "Update certificate to the database")
    @PutMapping("/{idCertificate}")
    public ResponseEntity<?> updateCertificate(@PathVariable("idCertificate") Long idCertificate
            , @RequestBody @Validated CertificateDto certificateDto, BindingResult bindingResult)
            throws CertificateNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new CertificateNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(certificateService.update(idCertificate, certificateMapper.toEntity(certificateDto)));
    }
    @Operation(summary = "Delete certificate from the database")
    @DeleteMapping("/{idCertificate}")
    public ResponseEntity<?> deleteLicense(@PathVariable("idCertificate") Long idCertificate) {
        return ResponseEntity.ok(certificateMapper.toDto(certificateService.delete(idCertificate)));
    }
}

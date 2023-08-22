package com.bank.publicinfo.controller.branchController;

import com.bank.publicinfo.dto.branchDto.AtmDto;
import com.bank.publicinfo.exception.branchException.AtmNotSaveException;
import com.bank.publicinfo.mapper.branchMapper.AtmMapper;
import com.bank.publicinfo.service.branchService.AtmService;
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

@Tag(name = "Atm's", description = "Atm's Management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/branch/{id}/atm")
public class AtmController {
    final AtmService atmService;
    final AtmMapper atmMapper;

    @Operation(summary = "Get all atm of atm's from the database")
    @GetMapping()
    public ResponseEntity<?> getAllAtm(@PathVariable("id") Long id) {
        return ResponseEntity.ok(atmMapper.toDtoList(atmService.getAll(id)));
    }

    @Operation(summary = "Get atm from the database by ID")
    @GetMapping("/{idAtm}")
    public ResponseEntity<?> getAtm(@PathVariable("idAtm") Long idLicense) {
        return ResponseEntity.ok(atmMapper.toDto(atmService.getById(idLicense)));
    }

    @Operation(summary = "Save atm to the database")
    @PostMapping
    public ResponseEntity<?> saveAtm(@PathVariable("id") Long id, @RequestBody @Validated AtmDto atmDto
            , BindingResult bindingResult) throws AtmNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new AtmNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(atmService.save(id, atmMapper.toEntity(atmDto)));
    }

    @Operation(summary = "Update atm to the database")
    @PutMapping("/{idAtm}")
    public ResponseEntity<?> updateAtm(@PathVariable("idAtm") Long idAtm
            , @RequestBody @Validated AtmDto atmDto, BindingResult bindingResult)
            throws AtmNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new AtmNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(atmService.update(idAtm, atmMapper.toEntity(atmDto)));
    }

    @Operation(summary = "Delete atm from the database")
    @DeleteMapping("/{idLicense}")
    public ResponseEntity<?> deleteAtm(@PathVariable("idLicense") Long idLicense) {
        return ResponseEntity.ok(atmMapper.toDto(atmService.delete(idLicense)));
    }
}

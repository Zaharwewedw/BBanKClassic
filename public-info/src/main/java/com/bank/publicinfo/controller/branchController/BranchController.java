package com.bank.publicinfo.controller.branchController;

import com.bank.publicinfo.dto.branchDto.BranchDto;
import com.bank.publicinfo.exception.branchException.BranchNotSaveException;
import com.bank.publicinfo.mapper.branchMapper.BranchMapper;
import com.bank.publicinfo.service.branchService.BranchService;
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

@Tag(name = "Branches", description = "Branches Management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/branch")
public class BranchController {
    final BranchService branchService;
    final BranchMapper branchMapper;

    @Operation(summary = "Get all branches from the database")
    @GetMapping
    public ResponseEntity<?> getAllBranches() {
        return ResponseEntity.ok(branchMapper.toDtoList(branchService.getAll()));
    }

    @Operation(summary = "Get branch from the database by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBranch(@PathVariable Long id) {
        return ResponseEntity.ok(branchMapper.toDto(branchService.getById(id)));
    }

    @Operation(summary = "Save branch to the database")
    @PostMapping
    public ResponseEntity<?> saveBranch(@RequestBody @Validated BranchDto branchDto
            , BindingResult bindingResult) throws BranchNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new BranchNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(branchService.save(branchMapper.toEntity(branchDto)));
    }

    @Operation(summary = "Update branch to the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(@PathVariable Long id
            , @RequestBody @Validated BranchDto branchDto
            , BindingResult bindingResult) throws BranchNotSaveException {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());

            }
            throw new BranchNotSaveException(errorMsg.toString());
        }
        return ResponseEntity.ok(branchService.update(id, branchMapper.toEntity(branchDto)));
    }

    @Operation(summary = "Delete branch from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long id) {
        return ResponseEntity.ok(branchMapper.toDto(branchService.delete(id)));
    }
}

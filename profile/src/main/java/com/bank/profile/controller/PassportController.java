package com.bank.profile.controller;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.service.passport.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportService passportService;
    @Autowired
    PassportMapper passportMapper;

    @GetMapping("/all")
    public ResponseEntity<List<PassportDTO>> getAllPassport() {
        return ResponseEntity.ok(
                passportMapper.listToDTO(
                        passportService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                passportMapper.passportToDTO(
                        passportService.getPassportById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<PassportDTO> addPassport(@RequestBody PassportDTO passportDTO) {
        Passport addedPassport = passportService.add(passportMapper.DTOToPassport(passportDTO));
        return ResponseEntity.ok(
                passportMapper.passportToDTO(addedPassport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id,
                                                   @RequestBody PassportDTO passportDTO) {

        Passport updated = passportService.update(id, passportMapper.DTOToPassport(passportDTO));
        return ResponseEntity.ok(
                passportMapper.passportToDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PassportDTO> deletePassport(@PathVariable Long id){
        Passport deletedPassport = passportService.deleteById(id);
        return ResponseEntity.ok(
                passportMapper.passportToDTO(deletedPassport));
    }
}

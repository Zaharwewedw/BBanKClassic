package com.bank.profile.controller;

import com.bank.profile.entity.Registration;
import com.bank.profile.service.reg.RegistrationService;
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
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService<Registration> registrationService;

    @GetMapping("/all")
    public ResponseEntity<List<Registration>> getAllRegistration() {
        return ResponseEntity.ok(registrationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationService.getRegistrationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Registration> addRegistration(@RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.add(registration));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id,
                                                           @RequestBody Registration registration) {
        Registration updatedRegistration = registrationService.update(id, registration);
        return ResponseEntity.ok(updatedRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Registration> deleteRegistration(@PathVariable Long id) {
        Registration registration = registrationService.deleteById(id);
        return ResponseEntity.ok(registration);
    }
}

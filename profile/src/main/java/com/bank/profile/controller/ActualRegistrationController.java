package com.bank.profile.controller;

import com.bank.profile.entity.ActualRegistration;
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
@RequestMapping("/actual-registration")
public class ActualRegistrationController {

    @Autowired
    private RegistrationService<ActualRegistration> registrationService;

    @GetMapping("/all")
    public ResponseEntity<List<ActualRegistration>> getAllRegistration() {
        return ResponseEntity.ok(registrationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActualRegistration> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationService.getRegistrationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ActualRegistration> addRegistration(@RequestBody ActualRegistration registration) {
        return ResponseEntity.ok(registrationService.add(registration));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualRegistration> updateRegistration(@PathVariable Long id,
                                                                 @RequestBody ActualRegistration registration) {
        ActualRegistration updatedRegistration = registrationService.update(id, registration);
        return ResponseEntity.ok(updatedRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActualRegistration> deleteRegistration(@PathVariable Long id) {
        ActualRegistration actualRegistration = registrationService.deleteById(id);
        return ResponseEntity.ok(actualRegistration);
    }
}

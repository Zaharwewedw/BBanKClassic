package com.bank.profile.controller;

import com.bank.profile.entity.AccountDetails;
import com.bank.profile.service.account.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-details")
public class AccountDetailsController {

    private final AccountDetailsService accountDetailsService;

    @Autowired
    public AccountDetailsController(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDetails>> getAllAccountDetails() {
        return ResponseEntity.ok(
                accountDetailsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                accountDetailsService.getAccountDetailsById(id)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDetails> addAccountDetails(@RequestBody AccountDetails accountDetails) {
        AccountDetails addedAccountDetails = accountDetailsService.add(accountDetails);
        return ResponseEntity.ok(
                addedAccountDetails
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDetails> deleteAccountDetails(@PathVariable Long id) {
        AccountDetails deletedAccountDetails = accountDetailsService.deleteById(id);
        return ResponseEntity.ok(
                deletedAccountDetails
        );
    }
}

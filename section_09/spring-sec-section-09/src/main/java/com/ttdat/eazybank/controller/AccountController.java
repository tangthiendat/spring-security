package com.ttdat.eazybank.controller;

import com.ttdat.eazybank.model.Accounts;
import com.ttdat.eazybank.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private AccountsRepository accountsRepository;

    @GetMapping
    public ResponseEntity<Accounts> getAccountDetails(@RequestParam int id) {
        return ResponseEntity.ok(accountsRepository.findByCustomerId(id));
    }

}

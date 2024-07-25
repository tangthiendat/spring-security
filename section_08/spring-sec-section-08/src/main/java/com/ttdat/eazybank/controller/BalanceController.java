package com.ttdat.eazybank.controller;

import com.ttdat.eazybank.model.AccountTransactions;
import com.ttdat.eazybank.repository.AccountTransactionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance")
@AllArgsConstructor
public class BalanceController {

    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping
    public ResponseEntity<List<AccountTransactions>> getBalanceDetails(@RequestParam int id) {
        return ResponseEntity.ok(accountTransactionsRepository
                .findByCustomerIdOrderByTransactionDtDesc(id));
    }
}

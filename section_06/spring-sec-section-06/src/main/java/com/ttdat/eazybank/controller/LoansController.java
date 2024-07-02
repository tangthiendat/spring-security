package com.ttdat.eazybank.controller;

import com.ttdat.eazybank.model.Loans;
import com.ttdat.eazybank.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoansController {

    private LoanRepository loanRepository;

    @GetMapping
    public ResponseEntity<List<Loans>> getLoanDetails(@RequestParam int id) {
        return ResponseEntity.ok(loanRepository.findByCustomerIdOrderByStartDtDesc(id));
    }

}

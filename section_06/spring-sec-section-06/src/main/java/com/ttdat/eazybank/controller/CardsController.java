package com.ttdat.eazybank.controller;

import com.ttdat.eazybank.model.Cards;
import com.ttdat.eazybank.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardsController {

    private CardsRepository cardsRepository;

    @GetMapping
    public List<Cards> getCardDetails(@RequestParam int id) {
        return cardsRepository.findByCustomerId(id);
    }

}

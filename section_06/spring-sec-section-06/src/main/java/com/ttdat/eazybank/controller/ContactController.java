package com.ttdat.eazybank.controller;

import com.ttdat.eazybank.model.Contact;
import com.ttdat.eazybank.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {

    private ContactRepository contactRepository;

    @PostMapping
    public ResponseEntity<Contact> saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }
}

package com.daliobank.accounts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.daliobank.accounts.model.dto.AccountRequestDTO;
import com.daliobank.accounts.model.dto.AccountResponseDTO;
import com.daliobank.accounts.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccountInfo")
    public ResponseEntity<AccountResponseDTO> getAccountInfo(@RequestParam Long accountNumber) {
        return ResponseEntity.ok(accountService.getAccountInfo(accountNumber));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.ok().body(accountService.saveAccount(accountRequestDTO));
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestParam Long accountNumber,@RequestParam Double amount) {      
        return ResponseEntity.ok().body(accountService.deposit(accountNumber, amount)); 
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawAmount(@RequestParam Long accountNumber,@RequestParam Double amount) {      
        return ResponseEntity.ok().body(accountService.withdraw(accountNumber, amount));
    }    
    
    
}

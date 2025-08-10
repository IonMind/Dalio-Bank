package com.daliobank.accounts.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("getAccountInfo")
    public ResponseEntity<String> getAccountInfo(@RequestParam Long accountId) {
        return ResponseEntity.ok("Account info for account ID: " + accountId);
    }

}

package com.daliobank.transaction_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daliobank.bankingcommons.dto.TransactionRequestDTO;
import com.daliobank.bankingcommons.dto.TransferRequestDTO;
import com.daliobank.bankingcommons.dto.TransferResponseDTO;
import com.daliobank.transaction_service.Model.Transaction;
import com.daliobank.transaction_service.service.TransactionService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getTransactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam Long accountNumber) {
        return ResponseEntity.ok().body(transactionService.getTransactionsByAccountNumber(accountNumber));
    }

    @PutMapping("/initTransaction")
    public ResponseEntity<Transaction> initiateTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return ResponseEntity.ok().body(transactionService.saveTransaction(transactionRequestDTO));

    }

    @PutMapping("/transfer")
    public ResponseEntity<TransferResponseDTO> initiateTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        return ResponseEntity.ok().body(transactionService.transferMoney(transferRequestDTO));
    }

}

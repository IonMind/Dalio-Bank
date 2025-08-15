package com.daliobank.transaction_service.service;

import java.util.List;

import com.daliobank.bankingcommons.dto.TransactionRequestDTO;
import com.daliobank.bankingcommons.dto.TransferRequestDTO;
import com.daliobank.bankingcommons.dto.TransferResponseDTO;
import com.daliobank.transaction_service.Model.Transaction;

public interface TransactionService {
    public List<Transaction> getTransactionsByAccountNumber(Long accountNumber);
    public Transaction saveTransaction(TransactionRequestDTO transactionRequestDTO);
    public TransferResponseDTO transferMoney(TransferRequestDTO transferRequestDTO);
}

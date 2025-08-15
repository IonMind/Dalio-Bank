package com.daliobank.transaction_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daliobank.bankingcommons.dto.TransactionRequestDTO;
import com.daliobank.bankingcommons.dto.TransferRequestDTO;
import com.daliobank.bankingcommons.dto.TransferResponseDTO;
import com.daliobank.bankingcommons.enums.TransactionType;
import com.daliobank.bankingcommons.exception.NoTransactionsFoundException;
import com.daliobank.transaction_service.Model.Transaction;
import com.daliobank.transaction_service.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Implement the methods defined in TransactionService interface
    @Override
    public List<Transaction> getTransactionsByAccountNumber(Long accountNumber) {
        List<Transaction> transactions = transactionRepository
                .findByAccountNumberOrderByTransactionTimeDesc(accountNumber)
                .orElse(null);
        if (transactions == null || transactions.isEmpty()) {
            throw new NoTransactionsFoundException("No transactions found for account number: " + accountNumber);
        }
        return transactions;
    }

    @Override
    public Transaction saveTransaction(TransactionRequestDTO transactionRequestDTO) {
        return createAndSaveTransaction(transactionRequestDTO.accountNumber(), transactionRequestDTO.amount(),
                TransactionType.fromString(transactionRequestDTO.transactionType()),
                transactionRequestDTO.description());
    }

    @Override
    @Transactional
    public TransferResponseDTO transferMoney(TransferRequestDTO transferRequestDTO) {

        Transaction debitTransaction = createAndSaveTransaction(transferRequestDTO.fromAccountNumber(),
                -transferRequestDTO.amount(), TransactionType.DEBIT, transferRequestDTO.description());

        Transaction creditTransaction = createAndSaveTransaction(transferRequestDTO.fromAccountNumber(),
                -transferRequestDTO.amount(), TransactionType.CREDIT, transferRequestDTO.description());

        return new TransferResponseDTO(debitTransaction.getTransactionId(), debitTransaction.getAccountNumber(),
                creditTransaction.getAccountNumber(), transferRequestDTO.amount(), transferRequestDTO.description(),
                debitTransaction.getTransactionTime());
    }

    private Transaction createAndSaveTransaction(Long accountNumber, Double amount, TransactionType transactionType,
            String description) {
        Transaction transaction = transactionRepository.save(new Transaction(accountNumber, amount,
                transactionType, description == null || description.isEmpty() ? "N.A." : description));
        return transaction;
    }

}

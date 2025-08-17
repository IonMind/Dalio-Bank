package com.daliobank.transaction_service.service;

import java.util.List;

import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daliobank.bankingcommons.dto.TransactionRequestDTO;
import com.daliobank.bankingcommons.dto.TransferRequestDTO;
import com.daliobank.bankingcommons.dto.TransferResponseDTO;
import com.daliobank.bankingcommons.enums.TransactionType;
import com.daliobank.bankingcommons.exception.DepositFailedException;
import com.daliobank.bankingcommons.exception.NoTransactionsFoundException;
import com.daliobank.transaction_service.Model.Transaction;
import com.daliobank.transaction_service.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

        @Autowired
        private TransactionRepository transactionRepository;

        @Autowired
        private AccountsServiceClient accountsServiceClient;

        @Override
        public List<Transaction> getTransactionsByAccountNumber(Long accountNumber) {
                if (accountsServiceClient.getAccountDetails(accountNumber) == null) {
                        log.error("Error while getting transactions for account number " + accountNumber);
                        throw new ExecutionException("Internal Server Error");
                }
                List<Transaction> transactions = transactionRepository
                                .findByAccountNumberOrderByTransactionTimeDesc(accountNumber)
                                .orElse(null);
                if (transactions == null || transactions.isEmpty()) {
                        throw new NoTransactionsFoundException(
                                        "No transactions found for account number: " + accountNumber);
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
        public TransferResponseDTO transferMoney(TransferRequestDTO transferRequestDTO) {

                Long sourceAccount = transferRequestDTO.fromAccountNumber();
                Long targetAccount = transferRequestDTO.toAccountNumber();
                Double amount = transferRequestDTO.amount();
                String description = transferRequestDTO.description();

                try {
                        accountsServiceClient.withdrawMoney(sourceAccount, amount);
                        Transaction debitTransaction = createAndSaveTransaction(sourceAccount, amount,
                                        TransactionType.DEBIT, description);

                        accountsServiceClient.depositMoney(targetAccount, amount);
                        Transaction creditTransaction = createAndSaveTransaction(targetAccount, amount,
                                        TransactionType.CREDIT, description);

                        return new TransferResponseDTO(debitTransaction.getTransactionId(),
                                        creditTransaction.getTransactionId(), sourceAccount,
                                        targetAccount,
                                        amount, description,
                                        debitTransaction.getTransactionTime());
                } catch (DepositFailedException e) {
                        accountsServiceClient.depositMoney(sourceAccount, amount);
                        createAndSaveTransaction(sourceAccount, amount, TransactionType.CREDIT, "rollback transfer");
                        throw new RuntimeException("Transfer Failed, not able to deposit to source account, rollback");
                }

        }

        private Transaction createAndSaveTransaction(Long accountNumber, Double amount, TransactionType transactionType,
                        String description) {
                if (accountsServiceClient.getAccountDetails(accountNumber) == null) {
                        log.error("Error while doing transaction for account number " + accountNumber);
                        throw new ExecutionException("Internal Server Error");
                }

                Transaction transaction = transactionRepository.save(new Transaction(accountNumber, amount,
                                transactionType,
                                description == null || description.isEmpty() ? "N.A." : description));
                return transaction;

        }

}

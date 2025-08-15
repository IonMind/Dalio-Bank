package com.daliobank.transaction_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daliobank.transaction_service.Model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Optional<List<Transaction>> findByAccountNumberOrderByTransactionTimeDesc(Long accountNumber);
}

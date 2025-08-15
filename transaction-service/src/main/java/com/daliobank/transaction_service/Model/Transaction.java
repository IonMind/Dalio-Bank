package com.daliobank.transaction_service.Model;

import java.time.LocalDateTime;

import com.daliobank.bankingcommons.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;
    @NonNull
    private Long accountNumber;
    @NonNull
    private Double amount;
    @NonNull
    private TransactionType transactionType;
    private LocalDateTime transactionTime;
    @NonNull
    private String description;

    @PrePersist
    protected void onCreate() {
        if (transactionTime == null) {
            transactionTime = LocalDateTime.now();
        }
    }

}

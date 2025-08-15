package com.daliobank.bankingcommons.dto;

public record TransactionResponseDTO(Long transactionId,
        Long accountNumber,
        double amount,
        String description,
        String transactionType,
        String timestamp) {
}
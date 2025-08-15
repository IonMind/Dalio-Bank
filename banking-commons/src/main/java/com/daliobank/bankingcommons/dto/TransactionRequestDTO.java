package com.daliobank.bankingcommons.dto;


public record TransactionRequestDTO(Long accountNumber,
                                    double amount,
                                    String description,
                                    String transactionType) {
    
}

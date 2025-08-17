package com.daliobank.bankingcommons.dto;

import java.time.LocalDateTime;

public record TransferResponseDTO(Long debitTransactionId,
        Long creditTransactionId,
        Long fromAccountNumber,
        Long toAccountNumber,
        double amount,
        String description,
        LocalDateTime timestamp) {

}

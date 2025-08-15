package com.daliobank.bankingcommons.dto;

public record TransferRequestDTO(Long fromAccountNumber,
                                  Long toAccountNumber,
                                  double amount,
                                  String description) {
    
}

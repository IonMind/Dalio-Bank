package com.daliobank.accounts.model.dto;

public record AccountResponseDTO(String accountNumber, String accountType, String accountHolderName, double balance) {
    
}

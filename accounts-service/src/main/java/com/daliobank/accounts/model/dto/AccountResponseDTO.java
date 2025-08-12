package com.daliobank.accounts.model.dto;

import com.daliobank.accounts.model.enums.AccountType;

public record AccountResponseDTO(Long accountNumber, AccountType accountType, String accountHolderName, double balance) {
    
}

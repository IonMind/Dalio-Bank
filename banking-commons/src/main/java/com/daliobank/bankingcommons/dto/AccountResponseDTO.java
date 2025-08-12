package com.daliobank.bankingcommons.dto;

import com.daliobank.bankingcommons.enums.AccountType;

public record AccountResponseDTO(Long accountNumber, AccountType accountType, String accountHolderName, double balance) {
    
}

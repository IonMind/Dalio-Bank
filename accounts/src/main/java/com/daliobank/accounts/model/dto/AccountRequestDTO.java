package com.daliobank.accounts.model.dto;

import com.daliobank.accounts.model.enums.AccountType;

public record AccountRequestDTO(AccountType accountType, String accountHolderName, double initialBalance) {
}

package com.daliobank.accounts.model.dto;

public record AccountRequestDTO(String accountType, String accountHolderName, double initialBalance) {
}

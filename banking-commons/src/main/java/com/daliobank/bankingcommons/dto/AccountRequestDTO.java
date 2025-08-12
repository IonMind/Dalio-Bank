package com.daliobank.bankingcommons.dto;

public record AccountRequestDTO(String accountType, String accountHolderName, double initialBalance) {
}

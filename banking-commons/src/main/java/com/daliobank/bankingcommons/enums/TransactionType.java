package com.daliobank.bankingcommons.enums;

import java.util.Arrays;

import com.daliobank.bankingcommons.exception.InvalidTransactionType;

public enum TransactionType {
    CREDIT,
    DEBIT,
    TRANSFER;

    public static TransactionType fromString(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTransactionType(
                    "Transaction type is invalid can be only from " + Arrays.asList(TransactionType.values()));
        } catch (NullPointerException e) {
            throw new InvalidTransactionType("Null value of transaction type is not allowed");
        }
    }

}
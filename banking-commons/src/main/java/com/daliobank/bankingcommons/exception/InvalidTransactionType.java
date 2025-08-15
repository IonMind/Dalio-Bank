package com.daliobank.bankingcommons.exception;


public class InvalidTransactionType extends RuntimeException {
    public InvalidTransactionType(String msg) {
        super(msg);
    }
}

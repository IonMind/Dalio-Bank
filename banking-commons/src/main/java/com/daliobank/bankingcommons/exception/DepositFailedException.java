package com.daliobank.bankingcommons.exception;

public class DepositFailedException extends RuntimeException {
        public DepositFailedException(String message) {
        super(message);
    }
}

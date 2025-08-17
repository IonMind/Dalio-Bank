package com.daliobank.bankingcommons.exception;

public class WithdrawFailedException extends RuntimeException {
        public WithdrawFailedException(String message) {
        super(message);
    }
}

package com.daliobank.accounts.exception;

public class InvalidAccountDetails extends RuntimeException {
    public InvalidAccountDetails(String message) {
        super(message);
    }
}

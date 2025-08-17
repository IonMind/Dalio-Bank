package com.daliobank.bankingcommons.exception;

//Used when creating account
public class InvalidAccountDetails extends RuntimeException {
    public InvalidAccountDetails(String message) {
        super(message);
    }
}

package com.daliobank.accountsservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.daliobank.bankingcommons.exception.AccountNotFoundException;
import com.daliobank.bankingcommons.exception.InsufficientFundsException;
import com.daliobank.bankingcommons.exception.InvalidAccountDetails;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(InvalidAccountDetails.class)
    public ResponseEntity<String> handleInvalidAccountDetails(InvalidAccountDetails ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<String> handleGenericError(Exception ex) {
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body("Something went wrong. Please try again later.");
    // }
}

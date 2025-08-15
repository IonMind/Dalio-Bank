package com.daliobank.transaction_service.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.daliobank.bankingcommons.exception.InvalidTransactionType;
import com.daliobank.bankingcommons.exception.NoTransactionsFoundException;

@ControllerAdvice
public class GlobalExceptionhandler {
    @ExceptionHandler(InvalidTransactionType.class)
    public ResponseEntity<String> handleInvalidTransactionType(InvalidTransactionType ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoTransactionsFoundException.class)
    public ResponseEntity<String> handleNoTransactionsFoundException(NoTransactionsFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex) {   
        ex.printStackTrace(); // Log the exception stack trace for debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Please try again later.");
    }
}
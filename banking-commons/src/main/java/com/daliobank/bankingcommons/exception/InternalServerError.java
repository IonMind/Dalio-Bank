package com.daliobank.bankingcommons.exception;

public class InternalServerError extends RuntimeException {
        public InternalServerError() {
        super("Internal Server Error, please try again later");
    }
}

package com.exalt.company.demo.exceptions;

public class BankAccountNotFoundException extends  RuntimeException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}

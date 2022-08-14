package com.exalt.company.exceptions;

/**
 * Exception thrown when account is not found in the database.
 */
public class BankAccountNotFoundException extends  RuntimeException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}

package com.exalt.company.exceptions;

/**
 * Exception thrown when there is problem of BankAccount's ID
 */
public class IdException extends  RuntimeException{
    public IdException(String message) {
        super(message);
    }
}

package com.exalt.company.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    BankAccount bankAccount;

    @BeforeEach
    public void initialisation() {
        bankAccount = new BankAccount(new BigDecimal(5000), Collections.emptySet());
    }

    @Test
    public void should_deposit_money_and_create_operation() {
        bankAccount.deposit(new BigDecimal(500));
        assertEquals(bankAccount.getBalance(),new BigDecimal(5500));
        assertEquals(1,bankAccount.getOperations().size());
    }
    @Test
    public void should_not_do_anything_when_amount_is_null() {
        bankAccount.deposit(null);
        assertEquals(bankAccount.getBalance(),new BigDecimal(5000));
        assertEquals(0,bankAccount.getOperations().size());
    }
    @Test
    public void should_withdraw_money_and_create_operation() {
        boolean isOperationDone = bankAccount.withdraw(new BigDecimal(500));
        assertEquals(bankAccount.getBalance(),new BigDecimal(4500));
        assertEquals(1,bankAccount.getOperations().size());
        assertTrue(isOperationDone);


    }
    @Test
    public void should_withdraw_when_amount_null() {
        boolean isOperationDone = bankAccount.withdraw(null);
        assertFalse(isOperationDone);

    }

}

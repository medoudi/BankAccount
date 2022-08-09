package com.exalt.company.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class BankAccount {
    private Long id;
    private BigDecimal balance;
    Set<Operation> operations;

    public BankAccount(BigDecimal balance, Set<Operation> operations) {
        this.balance = balance;
        this.operations = operations.stream().collect(Collectors.toSet());
    }

    public boolean withdraw(BigDecimal amount) {
        if(amount == null || balance.compareTo(amount) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        operations.add(createOperation(OperationType.WITHDRAWAL, amount));
        return true;
    }

    public void deposit(BigDecimal amount) {
        if(amount != null) {
            balance = balance.add(amount);
            operations.add(createOperation(OperationType.DEPOSIT, amount));
        }
    }

    Set<Operation> getOperations() {
        return operations;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    Operation createOperation(OperationType operationType, BigDecimal amount) {
        return new Operation(this, amount, LocalDate.now(), operationType);
    }

}
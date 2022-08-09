package com.exalt.company.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
    private Long id;
    BankAccount bankAccount;
    private BigDecimal amount;
    private LocalDate dateOfOperation;
    private OperationType operationType;

    public Operation(BankAccount bankAccount, BigDecimal amount, LocalDate dateOfOperation,
            OperationType operationType) {
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.operationType = operationType;
    }
}

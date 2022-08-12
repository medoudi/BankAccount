package com.exalt.company.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation implements Serializable {
    private Long id;
    @JsonIgnore
    private BankAccount bankAccount;
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

    public Operation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(LocalDate dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}

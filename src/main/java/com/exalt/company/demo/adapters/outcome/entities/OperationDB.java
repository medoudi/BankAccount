package com.exalt.company.demo.adapters.outcome.entities;

import com.exalt.company.demo.adapters.outcome.entities.enums.OperationTypeDB;
import com.exalt.company.demo.domain.OperationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "OPERATION")
public class OperationDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name="date_op")
    private LocalDate dateOfOperation;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationTypeDB operationTypeDB;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    public OperationDB(Long id, Account account, BigDecimal amount, LocalDate dateOfOperation,
            OperationTypeDB operationTypeDB) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.dateOfOperation = dateOfOperation;
        this.operationTypeDB = operationTypeDB;
    }

    public OperationDB() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public OperationTypeDB getOperationTypeDB() {
        return operationTypeDB;
    }

    public void setOperationType(OperationTypeDB operationTypeDB) {
        this.operationTypeDB = operationTypeDB;
    }
}

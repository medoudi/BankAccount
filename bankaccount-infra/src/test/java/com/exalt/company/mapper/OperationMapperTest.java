package com.exalt.company.mapper;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.domains.OperationType;
import com.exalt.company.entities.Account;
import com.exalt.company.entities.OperationDB;
import com.exalt.company.entities.enums.OperationTypeDB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class) class OperationMapperTest {

    @InjectMocks OperationMapper operationMapper;

    public void should_map_from_OperationDb_to_Operation() {
        Account account = new Account(1L, BigDecimal.valueOf(20000L));
        OperationDB operationDB = new OperationDB(1L, account, BigDecimal.valueOf(2040L), LocalDate.now(),
                OperationTypeDB.DEPOSIT);
        BankAccount bankAccount = new BankAccount();
        Operation operation = new Operation(bankAccount, BigDecimal.valueOf(200L), LocalDate.now(),
                OperationType.DEPOSIT);
        operationMapper.mapFromOperationDBToOperation(operationDB, operation);
        assertEquals(operation.getAmount(), operationDB.getAmount());
        assertEquals(operation.getDateOfOperation(), operationDB.getDateOfOperation());
        assertEquals(operation.getId(), operationDB.getId());
        assertEquals(operation.getOperationType(), OperationType.DEPOSIT);

    }

    @Test public void should_not_map_from_OperationDb_to_Operation_when_operation_is_null() {
        Account account = new Account(1L, BigDecimal.valueOf(20000L));
        OperationDB operationDB = new OperationDB(1L, account, BigDecimal.valueOf(2040L), LocalDate.now(),
                OperationTypeDB.DEPOSIT);
        Operation operation = null;
        operationMapper.mapFromOperationDBToOperation(operationDB, operation);
        assertNull(operation);

    }

    @Test public void should_not_map_from_OperationDb_to_Operation_when_operationDb_is_null() {
        BankAccount bankAccount = new BankAccount();
        Operation operation = new Operation(bankAccount, BigDecimal.valueOf(200L), LocalDate.now(),
                OperationType.DEPOSIT);
        OperationDB operationDB = null;
        operationMapper.mapFromOperationDBToOperation(operationDB, operation);
        assertNull(operationDB);

    }

    @Test public void should_map_from_Operation_to_OperationDb() {
        Account account = new Account(1L, BigDecimal.valueOf(20000L));
        OperationDB operationDB = new OperationDB(1L, account, BigDecimal.valueOf(2040L), LocalDate.now(),
                OperationTypeDB.DEPOSIT);
        BankAccount bankAccount = new BankAccount();
        Operation operation = new Operation(bankAccount, BigDecimal.valueOf(200L), LocalDate.now(),
                OperationType.DEPOSIT);
        operationMapper.mapFromOperationToOperationDB(operation, operationDB);
        assertEquals(operation.getAmount(), operationDB.getAmount());
        assertEquals(operation.getDateOfOperation(), operationDB.getDateOfOperation());
        assertEquals(operation.getId(), operationDB.getId());
        assertEquals(operationDB.getOperationTypeDB(), OperationTypeDB.DEPOSIT);

    }

    @Test public void should_not_map_from_Operation_to_OperationDb_when_operation_is_null() {
        Account account = new Account(1L, BigDecimal.valueOf(20000L));
        OperationDB operationDB = new OperationDB(1L, account, BigDecimal.valueOf(2040L), LocalDate.now(),
                OperationTypeDB.DEPOSIT);
        Operation operation = null;
        operationMapper.mapFromOperationToOperationDB(operation, operationDB);
        assertNull(operation);
    }

    @Test public void should_map_from_Operation_to_OperationDb_when_operation_is_null() {
        BankAccount bankAccount = new BankAccount();
        Operation operation = new Operation(bankAccount, BigDecimal.valueOf(200L), LocalDate.now(),
                OperationType.DEPOSIT);
        OperationDB operationDB = null;
        operationMapper.mapFromOperationToOperationDB(operation, operationDB);
        assertNull(operationDB);

    }

}

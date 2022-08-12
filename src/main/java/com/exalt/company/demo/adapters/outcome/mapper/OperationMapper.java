package com.exalt.company.demo.adapters.outcome.mapper;

import com.exalt.company.demo.adapters.outcome.entities.OperationDB;
import com.exalt.company.demo.adapters.outcome.entities.enums.OperationTypeDB;
import com.exalt.company.demo.domain.Operation;
import com.exalt.company.demo.domain.OperationType;

public class OperationMapper {
    public void mapFromOperationToOperationDB(Operation operation, OperationDB operationDB) {
        operationDB.setId(operation.getId());
        operationDB.setAmount(operation.getAmount());
        operationDB.setOperationType(operation.getOperationType() == OperationType.DEPOSIT ? OperationTypeDB.DEPOSIT : OperationTypeDB.WITHDRAWAL);
        operationDB.setDateOfOperation(operation.getDateOfOperation());
    }
    public void mapFromOperationDBToOperation(OperationDB operationDB,Operation operation) {
        operation.setId(operationDB.getId());
        operation.setAmount(operationDB.getAmount());
        operation.setDateOfOperation(operationDB.getDateOfOperation());
        operation.setOperationType(operationDB.getOperationTypeDB() == OperationTypeDB.DEPOSIT ? OperationType.DEPOSIT : OperationType.WITHDRAWAL);
    }

}

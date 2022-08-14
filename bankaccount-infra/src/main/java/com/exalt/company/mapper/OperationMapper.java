package com.exalt.company.mapper;

import com.exalt.company.domains.Operation;
import com.exalt.company.domains.OperationType;
import com.exalt.company.entities.OperationDB;
import com.exalt.company.entities.enums.OperationTypeDB;

/**
 * Mapper to map the Operation of Database to Operation of domain and vice versa
 */
public class OperationMapper {
    /**
     * map from Operation Of Database to Operation of the Domain
     * @param operation
     * @param operationDB
     */
    public void mapFromOperationToOperationDB(Operation operation, OperationDB operationDB) {
        if(operation != null && operationDB != null) {
            operationDB.setId(operation.getId());
            operationDB.setAmount(operation.getAmount());
            operationDB.setOperationTypeDB(operation.getOperationType() == OperationType.DEPOSIT ?
                    OperationTypeDB.DEPOSIT :
                    OperationTypeDB.WITHDRAWAL);
            operationDB.setDateOfOperation(operation.getDateOfOperation());
        }
    }

    /**
     * map from Operation Of the Domain  to Operation of the Database
     * @param operationDB
     * @param operation
     */
    public void mapFromOperationDBToOperation(OperationDB operationDB,Operation operation) {
        if(operation != null && operationDB != null) {
            operation.setId(operationDB.getId());
            operation.setAmount(operationDB.getAmount());
            operation.setDateOfOperation(operationDB.getDateOfOperation());
            operation.setOperationType(operationDB.getOperationTypeDB() == OperationTypeDB.DEPOSIT ?
                    OperationType.DEPOSIT :
                    OperationType.WITHDRAWAL);
        }
        }

}

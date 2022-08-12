package com.exalt.company.demo.adapters.outcome.mapper;

import com.exalt.company.demo.adapters.outcome.entities.Account;
import com.exalt.company.demo.adapters.outcome.entities.OperationDB;
import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;

import java.util.Set;
import java.util.stream.Collectors;

public class BankAccountMapper {

    private final OperationMapper operationMapper;

    public BankAccountMapper(OperationMapper operationMapper) {
        this.operationMapper = operationMapper;
    }

    public void mapFromAccountToBankAccount(Account account,BankAccount bankAccount) {
        bankAccount.setId(account.getId());
        bankAccount.setBalance(account.getBalance());
        Set<Operation> operationSet = mapSetOfOperationDbToSetOperations(account,bankAccount);
        bankAccount.setOperations(operationSet);

    }
    public void mapFromBankAccountToAccount(BankAccount bankAccount, Account account) {
        account.setId(bankAccount.getId());
        account.setBalance(bankAccount.getBalance());
        Set<OperationDB> operationDBSet = bankAccount.getOperations()
                .stream().map(op -> {
                    OperationDB opDB = new OperationDB();
                    opDB.setAccount(account);
                    operationMapper.mapFromOperationToOperationDB(op,opDB);
                    return opDB;
                }
                ).collect(Collectors.toSet());
        account.setOperations(operationDBSet);
    }
   public  Set<Operation> mapSetOfOperationDbToSetOperations(Account account,BankAccount bankAccount) {
      return  account.getOperations()
               .stream().map(opDB -> {
                           Operation op = new Operation();
                           op.setBankAccount(bankAccount);
                           operationMapper.mapFromOperationDBToOperation(opDB,op);
                           return op;
                       }
               ).collect(Collectors.toSet());
   }

}

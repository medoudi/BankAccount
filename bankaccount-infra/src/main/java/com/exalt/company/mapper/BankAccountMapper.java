package com.exalt.company.mapper;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.entities.Account;
import com.exalt.company.entities.OperationDB;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper to map from Account to bankAccount and vice versa
 */
public class BankAccountMapper {

    private final OperationMapper operationMapper;

    public BankAccountMapper(OperationMapper operationMapper) {
        this.operationMapper = operationMapper;
    }

    /**
     * Map from Account account of database to bankaccount of domain
     * @param account
     * @param bankAccount
     */
    public void mapFromAccountToBankAccount(Account account, BankAccount bankAccount) {
        if(account != null && bankAccount != null) {
            bankAccount.setId(account.getId());
            bankAccount.setBalance(account.getBalance());
            Set<Operation> operationSet = mapSetOfOperationDbToSetOperations(account, bankAccount);
            bankAccount.setOperations(operationSet);
        }

    }

    /**
     * map from bankaccount of domain to account of database
     * @param bankAccount
     * @param account
     */
    public void mapFromBankAccountToAccount(BankAccount bankAccount, Account account) {
        if(account != null && bankAccount != null) {
            account.setId(bankAccount.getId());
            account.setBalance(bankAccount.getBalance());
            Set<OperationDB> operationDBSet = bankAccount.getOperations() == null || bankAccount.getOperations().isEmpty()
                    ?  new HashSet<>() : bankAccount.getOperations().stream().map(op -> {
                OperationDB opDB = new OperationDB();
                opDB.setAccount(account);
                operationMapper.mapFromOperationToOperationDB(op, opDB);
                return opDB;
            }).collect(Collectors.toSet());
            account.setOperations(operationDBSet);
        }
    }

    /**
     * Map the operations of Database of the given Account to the bankAccount
     * @param account
     * @param bankAccount
     * @return
     */
   public  Set<Operation> mapSetOfOperationDbToSetOperations(Account account,BankAccount bankAccount) {
      return  account.getOperations() == null || account.getOperations().isEmpty()
              ?  new HashSet<>()
              : account.getOperations()
               .stream().map(opDB -> {
                           Operation op = new Operation();
                           op.setBankAccount(bankAccount);
                           operationMapper.mapFromOperationDBToOperation(opDB,op);
                           return op;
                       }
               ).collect(Collectors.toSet());
   }

}

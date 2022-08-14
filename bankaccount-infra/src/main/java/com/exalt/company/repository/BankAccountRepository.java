package com.exalt.company.repository;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.entities.Account;
import com.exalt.company.entities.OperationDB;
import com.exalt.company.mapper.BankAccountMapper;
import com.exalt.company.ports.outcome.IBankAccountRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Repository to interact with database.
 */
public class BankAccountRepository implements IBankAccountRepository {


    private SpringDataAccountRepository springDataAccountRepository;

    private BankAccountMapper bankAccountMapper;

    /**
     * Constructor
     * @param bankAccountMapper
     * @param springDataAccountRepository
     */
    public BankAccountRepository(BankAccountMapper bankAccountMapper,SpringDataAccountRepository springDataAccountRepository) {
        this.bankAccountMapper = bankAccountMapper;
        this.springDataAccountRepository = springDataAccountRepository;
    }

    /**
     * Lookup for an account of a given id
     * @param id
     * @return Optional of Account
     */
    @Override
    public Optional<BankAccount> findById(Long id) {
        Optional<Account> accountOptional = springDataAccountRepository.findById(id);
        if(accountOptional.isPresent()) {
            BankAccount bankAccount = new BankAccount();
            Account account = accountOptional.get();
            bankAccountMapper.mapFromAccountToBankAccount(account, bankAccount);
            return Optional.of(bankAccount);

        }
        return Optional.empty();
    }

    /**
     * update the balance in account after an operation deposit or withdraw
     * and add the operation in the list
     * @param bankAccount
     */
    @Override
    public void update(BankAccount bankAccount) {
       Optional<Account> accountOptional = springDataAccountRepository.findById(bankAccount.getId());
       if(accountOptional.isPresent()) {
           Account account = accountOptional.get();
           bankAccountMapper.mapFromBankAccountToAccount(bankAccount, account);
           springDataAccountRepository.save(account);
       }

    }

    /**
     * get the history of all operation on the given bankaccount
     * @param bankAccount
     * @return
     */
    @Override
    public Set<Operation> getHistory(BankAccount bankAccount) {
        Optional<Account> accountOptional = springDataAccountRepository.findById(bankAccount.getId());
        if(accountOptional.isPresent()) {
            Set<OperationDB> operationDBSs = accountOptional.get().getOperations();
            if(!operationDBSs.isEmpty()) {
                return bankAccountMapper.mapSetOfOperationDbToSetOperations(accountOptional.get(),bankAccount);
            }
        }
        return new HashSet<>();
    }
}

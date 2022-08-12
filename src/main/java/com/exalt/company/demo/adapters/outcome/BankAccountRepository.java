package com.exalt.company.demo.adapters.outcome;

import com.exalt.company.demo.adapters.outcome.entities.Account;
import com.exalt.company.demo.adapters.outcome.entities.OperationDB;
import com.exalt.company.demo.adapters.outcome.mapper.BankAccountMapper;
import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;
import com.exalt.company.demo.ports.outcome.SpringDataAccountRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class BankAccountRepository implements IBankAccountRepository {

    private SpringDataAccountRepository springDataAccountRepository;

    private BankAccountMapper bankAccountMapper;

    public BankAccountRepository(SpringDataAccountRepository springDataAccountRepository,BankAccountMapper bankAccountMapper) {
        this.bankAccountMapper = bankAccountMapper;
        this.springDataAccountRepository = springDataAccountRepository;
    }

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

    @Override
    public void update(BankAccount bankAccount) {
       Optional<Account> accountOptional = springDataAccountRepository.findById(bankAccount.getId());
       if(accountOptional.isPresent()) {
           Account account = accountOptional.get();
           bankAccountMapper.mapFromBankAccountToAccount(bankAccount, account);
           springDataAccountRepository.save(account);
       }

    }

    @Override
    public Set<Operation> getHistory(BankAccount bankAccount) {
        Optional<Account> accountOptional = springDataAccountRepository.findById(bankAccount.getId());
        if(accountOptional.isPresent()) {
            Set<OperationDB> operationDBSs = accountOptional.get().getOperations();
            if(!operationDBSs.isEmpty()) {
                return bankAccountMapper.mapSetOfOperationDbToSetOperations(accountOptional.get(),bankAccount);
            }
        }
        return Collections.emptySet();
    }
}

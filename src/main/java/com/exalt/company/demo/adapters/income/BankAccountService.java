package com.exalt.company.demo.adapters.income;

import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;
import com.exalt.company.demo.exceptions.BankAccountNotFoundException;
import com.exalt.company.demo.exceptions.IdException;
import com.exalt.company.demo.ports.income.IBankAccountService;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

public class BankAccountService implements IBankAccountService {


    private final IBankAccountRepository iBankAccountRepository;

    @Autowired
    public BankAccountService(IBankAccountRepository iBankAccountRepository) {
        this.iBankAccountRepository = iBankAccountRepository;
    }

    @Override
    public boolean withdraw(Long id, BigDecimal amount) {
        BankAccount bankAccount = getBankAccount(id);
        boolean hasWithdrawn = bankAccount.withdraw(amount);
        if(hasWithdrawn) {
            iBankAccountRepository.update(bankAccount);
        }
        return hasWithdrawn;
    }

    @Override
    public void deposit(Long id, BigDecimal amount) {
        BankAccount bankAccount = getBankAccount(id);
        bankAccount.deposit(amount);
        iBankAccountRepository.update(bankAccount);
    }
    @Override
    public Set<Operation> getHistory(Long id) {
        BankAccount bankAccount = getBankAccount(id);
        return iBankAccountRepository.getHistory(bankAccount);
    }


    public BankAccount getBankAccount(Long id ) {
        if(id != null ) {
            return iBankAccountRepository.findById(id).<BankAccountNotFoundException>orElseThrow(() -> {throw new BankAccountNotFoundException("Bank Account Not found");});
        }
        else {
            throw new IdException("Id can not be null");
        }

    }
}

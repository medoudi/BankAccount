package com.exalt.company.demo.ports.outcome;

import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;

import java.util.Optional;
import java.util.Set;

public interface IBankAccountRepository {
    public abstract Optional<BankAccount> findById(Long id);
    public abstract void update(BankAccount bankAccount);
    public abstract Set<Operation> getHistory(BankAccount bankAccount);
}

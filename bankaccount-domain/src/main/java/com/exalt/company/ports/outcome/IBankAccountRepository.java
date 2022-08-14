package com.exalt.company.ports.outcome;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;

import java.util.Optional;
import java.util.Set;

/**
 * Interface of the different interaction done with the infra-module
 */
public interface IBankAccountRepository {
    public abstract Optional<BankAccount> findById(Long id);
    public abstract void update(BankAccount bankAccount);
    public abstract Set<Operation> getHistory(BankAccount bankAccount);
}

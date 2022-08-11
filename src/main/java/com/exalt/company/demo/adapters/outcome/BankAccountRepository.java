package com.exalt.company.demo.adapters.outcome;

import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BankAccountRepository implements IBankAccountRepository {

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(BankAccount bankAccount) {

    }

    @Override
    public Set<Operation> getHistory(BankAccount bankAccount) {
        return null;
    }
}

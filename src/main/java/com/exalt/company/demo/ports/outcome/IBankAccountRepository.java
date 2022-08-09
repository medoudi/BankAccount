package com.exalt.company.demo.ports.outcome;

import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;


import java.util.List;

public interface IBankAccountRepository {
    public abstract void update(BankAccount bankAccount);
    public abstract List<Operation> getHistory(BankAccount bankAccount);
}

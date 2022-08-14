package com.exalt.company.ports.income;

import com.exalt.company.domains.Operation;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Interface of the different services provided by the domain to app module.
 */
public interface IBankAccountService {
    boolean withdraw(Long id, BigDecimal amount);
    void deposit(Long id, BigDecimal amount);
    Set<Operation> getHistory(Long id);

}

package com.exalt.company.demo.ports.income;

import com.exalt.company.demo.domain.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface IBankAccountService {
    boolean withdraw(Long id, BigDecimal amount);
    void deposit(Long id, BigDecimal amount);
    List<Operation> getistory(Long id);

}

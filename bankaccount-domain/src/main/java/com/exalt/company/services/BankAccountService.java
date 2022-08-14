package com.exalt.company.services;


import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.exceptions.BankAccountNotFoundException;
import com.exalt.company.exceptions.IdException;
import com.exalt.company.ports.income.IBankAccountService;
import com.exalt.company.ports.outcome.IBankAccountRepository;


import java.math.BigDecimal;
import java.util.Set;

/**
 * Class Service represents differents functionalities offered by the domain
 */
public class BankAccountService implements IBankAccountService {


    private final IBankAccountRepository iBankAccountRepository;

    /**
     * Constructor
     * @param iBankAccountRepository
     */
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

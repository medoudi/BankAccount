package com.exalt.company.mapper;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.domains.OperationType;
import com.exalt.company.entities.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class) class BankAccountMapperTest {
    @InjectMocks BankAccountMapper bankAccountMapper;
    @Mock OperationMapper operationMapper;

    @Test 
    public void should_map_bank_account_from_data_base_to_domain() {
        Account account = new Account(1L, BigDecimal.valueOf(20000L));
        BankAccount bankAccount = new BankAccount();
        bankAccountMapper.mapFromAccountToBankAccount(account, bankAccount);
        assertEquals(account.getBalance(), bankAccount.getBalance());
        assertEquals(bankAccount.getId(), account.getId());

    }

    @Test
    public void should_not_map_from_account_to_bankAccount_when_account_is_null() {
        Account account = null;
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(BigDecimal.valueOf(2000L));
        bankAccountMapper.mapFromAccountToBankAccount(account, bankAccount);
        assertNull(account);

    }

    @Test
    public void should_not_map_from_account_to_bankAccount_when_bankaccount_is_null() {
        Account account = new Account(3L, BigDecimal.valueOf(3000L));
        BankAccount bankAccount = null;
        bankAccountMapper.mapFromAccountToBankAccount(account, bankAccount);
        assertNull(bankAccount);

    }

    @Test 
    public void should_map_from_bank_account_to_account() {
        Account account = new Account();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(BigDecimal.valueOf(2000L));
        Operation operation1 = new Operation(bankAccount, BigDecimal.valueOf(200L), LocalDate.now(),
                OperationType.DEPOSIT);
        Operation operation2 = new Operation(bankAccount, BigDecimal.valueOf(100L), LocalDate.now().minusDays(3),
                OperationType.WITHDRAWAL);
        bankAccount.setOperations(new HashSet<Operation>());
        bankAccount.getOperations().add(operation1);
        bankAccount.getOperations().add(operation2);
        bankAccountMapper.mapFromBankAccountToAccount(bankAccount, account);
        assertEquals(account.getBalance(), bankAccount.getBalance());
        assertEquals(bankAccount.getId(), account.getId());
        assertEquals(bankAccount.getOperations().size(), account.getOperations().size());
    }

    @Test 
    public void should_not_map_from_bankaccount_to_account_when_bankAccount_is_null() {
        Account account = new Account(3L, BigDecimal.valueOf(3000L));
        BankAccount bankAccount = null;
        bankAccountMapper.mapFromBankAccountToAccount(bankAccount, account);
        assertNull(bankAccount);

    }

    @Test 
    public void should_not_map_from_bankaccount_to_account_when_account_is_null() {
        Account account = null;
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(BigDecimal.valueOf(2000L));
        bankAccountMapper.mapFromBankAccountToAccount(bankAccount, null);
        assertNull(account);

    }

}

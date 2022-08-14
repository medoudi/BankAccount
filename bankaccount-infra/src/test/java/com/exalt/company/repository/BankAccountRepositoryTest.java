package com.exalt.company.repository;

import com.exalt.company.domains.BankAccount;
import com.exalt.company.domains.Operation;
import com.exalt.company.domains.OperationType;
import com.exalt.company.entities.Account;
import com.exalt.company.entities.OperationDB;
import com.exalt.company.entities.enums.OperationTypeDB;
import com.exalt.company.mapper.BankAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Test Class for BankAccountRepository
 */
@ExtendWith(MockitoExtension.class)
class BankAccountRepositoryTest {
    @InjectMocks
    private BankAccountRepository bankAccountRepository;

    @Mock
    private SpringDataAccountRepository springDataAccountRepository;

    @Mock
    private BankAccountMapper bankAccountMapper;

    private Account account;
    Answer<Void> mapToBankAccount ;
    Answer<Void> mapToAccount ;

    @BeforeEach
    public void init() {
         account = new Account(1L, BigDecimal.valueOf(20000));
        OperationDB op1 = new OperationDB(1L,account,BigDecimal.valueOf(2000), LocalDate.now(), OperationTypeDB.WITHDRAWAL);
        OperationDB op2 = new OperationDB(2L,account,BigDecimal.valueOf(5000), LocalDate.now(), OperationTypeDB.DEPOSIT);
        Set<OperationDB> operationDBSet = new HashSet<>();
        operationDBSet.add(op1);
        operationDBSet.add(op2);
        account.setOperations(operationDBSet);
        mapToBankAccount = invocation -> {
            Account account = invocation.getArgument(0, Account.class);
            BankAccount bankAccount = invocation.getArgument(1, BankAccount.class);
           bankAccount.setBalance(account.getBalance());
           bankAccount.setId(account.getId());
            return null;
        };

        mapToAccount = invocation -> {
            BankAccount bankAccount = invocation.getArgument(0, BankAccount.class);
            Account account = invocation.getArgument(1, Account.class);
            account.setBalance(bankAccount.getBalance());
            account.setId(bankAccount.getId());
            return null;
        };
    }

    @Test
    public void should_create_and_return_bankAccount_with_id_equals_1() {
        when(springDataAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        doAnswer(mapToBankAccount).when(bankAccountMapper).mapFromAccountToBankAccount(any(Account.class),any(BankAccount.class));
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(1L);
        assertThat(bankAccount).isNotEmpty();
        assertTrue(bankAccount.isPresent());
        assertEquals(bankAccount.get().getId(),1L);
        assertEquals(bankAccount.get().getBalance(),BigDecimal.valueOf(20000));
    }

    @Test
    public void should_return_empty_optional_with_id_equals_2() {
        when(springDataAccountRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(2L);
        assertThat(bankAccount).isEmpty();
        assertFalse(bankAccount.isPresent());

    }

    @Test
    public void should_save_account_after_update() {
        when(springDataAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(BigDecimal.valueOf(30000));
        doAnswer(mapToAccount).when(bankAccountMapper).mapFromBankAccountToAccount(bankAccount,account);
        bankAccountRepository.update(bankAccount);
        assertEquals(account.getBalance(),BigDecimal.valueOf(30000));

    }

    @Test
    public void should_return_list_of_operations() {
        when(springDataAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(BigDecimal.valueOf(20000));
        Set<Operation> operations = new HashSet<>();
        Operation op1 = new Operation(bankAccount,BigDecimal.valueOf(2000), LocalDate.now(), OperationType.WITHDRAWAL);
        Operation op2 = new Operation(bankAccount,BigDecimal.valueOf(5000), LocalDate.now(), OperationType.DEPOSIT);
        operations.add(op1);
        operations.add(op2);
        when(bankAccountMapper.mapSetOfOperationDbToSetOperations(account,bankAccount)).thenReturn(operations);
        Set<Operation> operationsSet= bankAccountRepository.getHistory(bankAccount);
        assertEquals(2,operationsSet.size());
        assertTrue(operationsSet.contains(op1));

    }

}

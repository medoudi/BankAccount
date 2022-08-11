package com.exalt.company.demo.adapters.income;

import com.exalt.company.demo.domain.BankAccount;
import com.exalt.company.demo.domain.Operation;
import com.exalt.company.demo.domain.OperationType;
import com.exalt.company.demo.exceptions.BankAccountNotFoundException;
import com.exalt.company.demo.exceptions.IdException;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {
    @InjectMocks
    BankAccountService bankAccountService;

    @Mock
    private IBankAccountRepository iBankAccountRepository;

    @Test
    public void should_throw_idException_when_id_is_null() {
       Exception exception = assertThrows(IdException.class , () -> { bankAccountService.getBankAccount(null);});
        String expectedMessage = "Id can not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void should_throw_bankaccountfoundexception_when_account_not_found() {
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.empty());
        Exception exception =  assertThrows(
                BankAccountNotFoundException.class , () -> { bankAccountService.getBankAccount(5500L);});
        String expectedMessage = "Bank Account Not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_return_bankaccount() {
        BankAccount bankAccount = new BankAccount(new BigDecimal(200),Collections.emptySet());
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.of(bankAccount));
        assertNotNull(bankAccountService.getBankAccount(5500L));
    }

    @Test
    public void should_not_withdraw_when_amount_is_less_the_balance_in_the_account() {
        BankAccount bankAccount = new BankAccount(new BigDecimal(200),Collections.emptySet());
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.of(bankAccount));
        boolean isOperationDone = bankAccountService.withdraw(5500L, BigDecimal.valueOf(500));
        assertFalse(isOperationDone);
    }

    @Test
    public void should_withdraw_when_account_found_and_amount_is_more_than_balance() {
        BankAccount bankAccount = new BankAccount(new BigDecimal(2000),Collections.emptySet());
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.of(bankAccount));
        boolean isOperationDone = bankAccountService.withdraw(5500L, BigDecimal.valueOf(500));
        assertTrue(isOperationDone);
        assertEquals(bankAccount.getBalance(),BigDecimal.valueOf(1500));
    }

    @Test
    public void should_deposit_amount_in_account() {
        BankAccount bankAccount = new BankAccount(new BigDecimal(2000),Collections.emptySet());
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.of(bankAccount));
        bankAccountService.deposit(5500L, BigDecimal.valueOf(500));
        assertEquals(bankAccount.getBalance(),BigDecimal.valueOf(2500));
    }

    @Test
    public void should_return_list_operations() {
        BankAccount bankAccount = new BankAccount(new BigDecimal(2000),Collections.emptySet());
        Set<Operation> mockOperationSet = new HashSet<Operation>();
        mockOperationSet.add(new Operation(bankAccount, BigDecimal.valueOf(200), LocalDate.now(), OperationType.DEPOSIT));
        mockOperationSet.add(new Operation(bankAccount, BigDecimal.valueOf(1400), LocalDate.now().minusDays(2), OperationType.DEPOSIT)) ;
        mockOperationSet.add(new Operation(bankAccount, BigDecimal.valueOf(700), LocalDate.now().plusDays(3), OperationType.WITHDRAWAL)) ;
        bankAccount.setOperations(mockOperationSet);
        when(iBankAccountRepository.getHistory(bankAccount)).thenReturn(mockOperationSet);
        when(iBankAccountRepository.findById(5500L)).thenReturn(Optional.of(bankAccount));
        bankAccountService.getHistory(5500L);
        assertEquals(3,bankAccount.getOperations().size());
    }

}

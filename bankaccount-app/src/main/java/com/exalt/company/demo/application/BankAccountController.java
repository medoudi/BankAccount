package com.exalt.company.demo.application;

import com.exalt.company.domains.Operation;
import com.exalt.company.ports.income.IBankAccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private final IBankAccountService bankAccountService;

    public BankAccountController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;

    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    void deposit(@PathVariable final Long id,
            @PathVariable final BigDecimal amount) {
        bankAccountService.deposit(id, amount);

    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    void withdraw(@PathVariable final Long id,
            @PathVariable final BigDecimal amount) {
        bankAccountService.withdraw(id, amount);
    }
    @GetMapping(value = "/{id}/list")
    @ResponseBody Set<Operation> getHistory(@PathVariable final Long id) {
       return bankAccountService.getHistory(id);
    }
}



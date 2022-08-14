package com.exalt.company.demo.configuration;

import com.exalt.company.mapper.BankAccountMapper;
import com.exalt.company.mapper.OperationMapper;
import com.exalt.company.ports.income.IBankAccountService;
import com.exalt.company.ports.outcome.IBankAccountRepository;
import com.exalt.company.repository.BankAccountRepository;
import com.exalt.company.repository.SpringDataAccountRepository;
import com.exalt.company.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration @ComponentScan(basePackages = { "com.exalt.company.demo.application", "com.exalt.company.mapper",
        "com.exalt.company.repository", "com.exalt.company.ports.income", "com.exalt.company.services",
        " com.exalt.company.ports.outcome" }) public class BeanConfiguration {
    @Autowired SpringDataAccountRepository springDataAccountRepository;

    @Bean public IBankAccountRepository iBankAccountRepository() {
        return new BankAccountRepository(bankAccountMapper(), springDataAccountRepository);
    }

    @Bean

    public IBankAccountService bankAccountService() {
        return new BankAccountService(iBankAccountRepository());
    }

    @Bean public OperationMapper operationMapper() {
        return new OperationMapper();
    }

    @Bean public BankAccountMapper bankAccountMapper() {
        return new BankAccountMapper(operationMapper());
    }

}

package com.exalt.company.configuration;

import com.exalt.company.mapper.BankAccountMapper;
import com.exalt.company.mapper.OperationMapper;
import com.exalt.company.ports.outcome.IBankAccountRepository;
import com.exalt.company.repository.BankAccountRepository;
import com.exalt.company.repository.SpringDataAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.exalt.company.repository")
public class BeanInfraConfiguration {
    @Autowired
    private SpringDataAccountRepository springDataAccountRepository;
    @Bean
    public IBankAccountRepository iBankAccountRepository() {
        return new BankAccountRepository(bankAccountMapper(),springDataAccountRepository);
    }

    @Bean
    public OperationMapper operationMapper() {return new OperationMapper();}

    @Bean
    public BankAccountMapper bankAccountMapper() {return new BankAccountMapper(operationMapper());}

}

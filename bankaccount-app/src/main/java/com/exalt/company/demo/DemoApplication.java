package com.exalt.company.demo;

import com.exalt.company.configuration.BeanInfraConfiguration;
import com.exalt.company.ports.income.IBankAccountService;
import com.exalt.company.ports.outcome.IBankAccountRepository;
import com.exalt.company.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration @Configuration @EntityScan(basePackages = {
        "com.exalt.company.entities" }) @ComponentScan(basePackages = { "com.exalt.company.demo.application",
        "com.exalt.company.mapper", "com.exalt.company.repository", "com.exalt.company.ports.income",
        "com.exalt.company.services",
        " com.exalt.company.ports.outcome" }) @Import(BeanInfraConfiguration.class) public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired IBankAccountRepository iBankAccountRepository;

    @Bean public IBankAccountService bankAccountService() {
        return new BankAccountService(iBankAccountRepository);
    }

}

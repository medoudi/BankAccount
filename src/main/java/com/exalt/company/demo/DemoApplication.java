package com.exalt.company.demo;

import com.exalt.company.demo.adapters.income.BankAccountService;
import com.exalt.company.demo.adapters.outcome.BankAccountRepository;
import com.exalt.company.demo.ports.income.IBankAccountService;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.exalt.company.demo.adapters.income",
		"com.exalt.company.demo.adapters.outcome"})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public IBankAccountRepository iBankAccountRepository() {
		return new BankAccountRepository();
	}

	@Bean
	public IBankAccountService bankAccountService()
	{
		return new BankAccountService(iBankAccountRepository());
	}

}


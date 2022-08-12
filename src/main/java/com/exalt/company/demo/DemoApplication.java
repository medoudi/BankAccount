package com.exalt.company.demo;

import com.exalt.company.demo.adapters.income.BankAccountService;
import com.exalt.company.demo.adapters.outcome.BankAccountRepository;
import com.exalt.company.demo.adapters.outcome.mapper.BankAccountMapper;
import com.exalt.company.demo.adapters.outcome.mapper.OperationMapper;
import com.exalt.company.demo.aspect.LoggingAspect;
import com.exalt.company.demo.ports.income.IBankAccountService;
import com.exalt.company.demo.ports.outcome.IBankAccountRepository;
import com.exalt.company.demo.ports.outcome.SpringDataAccountRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.exalt.company.demo.adapters.income",
		"com.exalt.company.demo.aspect",
		"com.exalt.company.demo.adapters.outcome","com.exalt.company.demo.adapters.web.client",
		"com.exalt.company.demo.adapters.outcome.mapper"})
public class DemoApplication {
	@Autowired
	SpringDataAccountRepository springDataAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public OperationMapper operationMapper() {return new OperationMapper();}

	@Bean
	public BankAccountMapper bankAccountMapper() {return new BankAccountMapper(operationMapper());}

	@Bean
	public IBankAccountRepository iBankAccountRepository() {
		return new BankAccountRepository(springDataAccountRepository,bankAccountMapper());
	}

	@Bean
	public IBankAccountService bankAccountService()
	{
		return new BankAccountService(iBankAccountRepository());
	}


}


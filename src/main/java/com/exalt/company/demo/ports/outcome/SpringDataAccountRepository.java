package com.exalt.company.demo.ports.outcome;

import com.exalt.company.demo.adapters.outcome.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<Account,Long> {

}

package com.learning.accounts.repository;

import com.learning.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.accounts.entity.Accounts;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{

    Optional<Accounts> findByCustomerId(Long customerId);
}

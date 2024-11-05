package com.learning.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	

	Optional<Customer> findByMobileNumber(String mobileNumber);
	
	

}

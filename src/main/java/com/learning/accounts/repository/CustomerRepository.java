package com.learning.accounts.repository;

import com.learning.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	

	Optional<Customer> findByMobileNumber(String mobileNumber);
	
	

}

package com.learning.accounts.service;


import com.learning.accounts.dto.AccountsDTO;
import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.repository.AccountsRepository;
import com.learning.accounts.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AccountsService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public String createAccount(CustomerDetailsDTO customerDetailsDTO) {
		
		Customer customer = new Customer();
		//Will copy only those properties which have matching field names
		BeanUtils.copyProperties(customerDetailsDTO, customer);
	
		
		// verification against mobile number
		Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDetailsDTO.getMobileNumber());

		if(existingCustomer.isPresent()) {
			throw new RuntimeException("Customer already exists with mobile number "+ customerDetailsDTO.getMobileNumber());
		}

		Customer dbCustomer = customerRepository.save(customer);

		long accountNumber = createAccountEntity(dbCustomer);
		return String.valueOf(accountNumber);
	}

	private long createAccountEntity(Customer dbCustomer) {
		Accounts accounts = new Accounts();

		accounts.setAccountType("SAVINGS");
		accounts.setBranchAddress("BLR");
		accounts.setCustomerId(dbCustomer.getCustomerId());

		long accountNumber = new Random().nextLong(1000000000L) + 1000000000L;

		accounts.setAccountNumber(accountNumber);

		accountsRepository.save(accounts);
		return accountNumber;
	}

	public Accounts fetchAccount(Long accountNumber){
		return accountsRepository.findById(accountNumber).orElse(new Accounts());
	}


    public CustomerDetailsDTO updateAccountInformation(CustomerDetailsDTO customerDetailsDTO) {
		// first check if account exists
		AccountsDTO accountsDTO = customerDetailsDTO.getAccountsDTO();
		if(accountsDTO!=null){
			Accounts dbAccounts = accountsRepository.findById(accountsDTO.getAccountNumber())
					.orElseThrow(() -> new RuntimeException("Accounts not found"));
			BeanUtils.copyProperties(accountsDTO, dbAccounts);
			dbAccounts = accountsRepository.save(dbAccounts);
			Long customerId = dbAccounts.getCustomerId();
			Customer dbCustomer = customerRepository.findById(customerId)
					.orElseThrow(() -> new RuntimeException("Customer Not Found"));

			BeanUtils.copyProperties(customerDetailsDTO, dbCustomer);
			customerRepository.save(dbCustomer);
		}
		else{
			throw new RuntimeException("Account info is missing");
		}
		return customerDetailsDTO;
    }

	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new RuntimeException("No Customer with mobile number " + mobileNumber));

		Long dbCustomerId = customer.getCustomerId();

		accountsRepository.deleteByCustomerId(dbCustomerId);
		customerRepository.deleteById(dbCustomerId);
//		Accounts dbAccount = accountsRepository.findByCustomerId(dbCustomerId)
//				.orElseThrow(() -> new RuntimeException("No Account with customer id " + dbCustomerId));
//
//		accountsRepository.deleteById(dbAccount.getAccountNumber());
//		customerRepository.deleteById(dbCustomerId);

		return true;

	}
}

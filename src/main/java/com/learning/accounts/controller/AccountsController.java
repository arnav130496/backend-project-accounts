package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	
	//Create an Account
	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody CustomerDetailsDTO customerDetailsDTO){
		log.info("Create a new account");
		String output = accountsService.createAccount(customerDetailsDTO);
		log.info("New account created");
		return new ResponseEntity<>(output, HttpStatus.CREATED);
	}
	
	//Fetch an Account
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Accounts> fetchAccount(@PathVariable Long id){
		log.info("Fetch account using account ID {}", id);
		Accounts output = accountsService.fetchAccount(id);
		if(output.equals(new Accounts())){
			return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
	}


	//Update an Account
	@PutMapping
	public CustomerDetailsDTO updateAccount(@RequestBody CustomerDetailsDTO customerDetailsDTO){
		log.info("Update an account");
		return accountsService.updateAccountInformation(customerDetailsDTO);
	}
	
	//Delete an Account
	@DeleteMapping
	public boolean deleteAccount(@RequestParam String mobileNumber){
		log.info("Delete an account");
		return accountsService.deleteAccount(mobileNumber);
	}

}

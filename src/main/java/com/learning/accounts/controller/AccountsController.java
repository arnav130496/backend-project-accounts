package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	
	//Create an Account
	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody CustomerDetailsDTO customerDetailsDTO){
		String output = accountsService.createAccount(customerDetailsDTO);
		return new ResponseEntity<>(output, HttpStatus.CREATED);
	}
	
	//Fetch an Account
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Accounts> fetchAccount(@PathVariable Long id){
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
		return accountsService.updateAccountInformation(customerDetailsDTO);
	}
	
	//Delete an Account
	@DeleteMapping
	public boolean deleteAccount(@RequestParam String mobileNumber){
		return accountsService.deleteAccount(mobileNumber);
	}

}

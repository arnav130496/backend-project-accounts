package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.entity.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learning.accounts.dto.AccountsDTO;
import com.learning.accounts.service.AccountsService;

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
	
	//Delete an Account

}

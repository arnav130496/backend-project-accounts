package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    //Fetch a Customer By Mobile Number
    @GetMapping
    public ResponseEntity<CustomerDetailsDTO> fetchAccountByMobileNumber(@RequestParam String mobile){
        CustomerDetailsDTO output = customerService.fetchAccountByMobileNumber(mobile);
        if(output.equals(new CustomerDetailsDTO())){
            return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
    }

}

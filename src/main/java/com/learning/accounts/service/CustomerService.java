package com.learning.accounts.service;

import com.learning.accounts.dto.AccountsDTO;
import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.repository.AccountsRepository;
import com.learning.accounts.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDetailsDTO fetchAccountByMobileNumber(String mobile){

        Customer customer = customerRepository.findByMobileNumber(mobile).orElse(new Customer());
        Accounts output = accountsRepository.findByCustomerId(customer.getCustomerId()).orElse(new Accounts());

        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
        BeanUtils.copyProperties(customer, customerDetailsDTO);

        AccountsDTO accountsDTO = new AccountsDTO();
        BeanUtils.copyProperties(output, accountsDTO);
        customerDetailsDTO.setAccountsDTO(accountsDTO);
        return customerDetailsDTO;
    }


}

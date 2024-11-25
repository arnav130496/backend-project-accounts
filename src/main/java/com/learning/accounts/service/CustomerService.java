package com.learning.accounts.service;

import com.learning.accounts.dto.AccountsDTO;
import com.learning.accounts.dto.CustomerDetailsDTO;
import com.learning.accounts.dto.cards.CardsDto;
import com.learning.accounts.dto.loans.LoansDto;
import com.learning.accounts.entity.Accounts;
import com.learning.accounts.entity.Customer;
import com.learning.accounts.feignclients.CardsFeignClient;
import com.learning.accounts.feignclients.LoansFeignClient;
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

    @Autowired
    CardsFeignClient cardsFeignClient;

    @Autowired
    LoansFeignClient loansFeignClient;

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


    public CustomerDetailsDTO fetchCustomerInfo(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("No Customer exists with mobile number " + mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new RuntimeException("No Account exists with customer ID " + customer.getCustomerId())
        );

        CardsDto cards = cardsFeignClient.getCard(mobileNumber).getBody();
        LoansDto loans = loansFeignClient.getLoan(mobileNumber).getBody();

        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
        AccountsDTO accountsDTO = new AccountsDTO();
        BeanUtils.copyProperties(accounts,accountsDTO);
        customerDetailsDTO.setAccountsDTO(accountsDTO);
        customerDetailsDTO.setCardsDto(cards);
        customerDetailsDTO.setLoansDto(loans);

        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmailId(customer.getEmailId());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());

        return customerDetailsDTO;

    }
}

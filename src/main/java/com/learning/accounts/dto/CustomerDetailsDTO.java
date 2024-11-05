package com.learning.accounts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDetailsDTO {
	
	private String name;
	private String emailId;
	private String mobileNumber;
	
	AccountsDTO accountsDTO;

}

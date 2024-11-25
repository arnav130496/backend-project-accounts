package com.learning.accounts.dto;

import com.learning.accounts.dto.cards.CardsDto;
import com.learning.accounts.dto.loans.LoansDto;
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
	CardsDto cardsDto;
	LoansDto loansDto;

}

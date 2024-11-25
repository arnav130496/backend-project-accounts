package com.learning.accounts.dto.cards;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardsDto {

    private String mobileNumber;
    private String cardNumber; // generate random number
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}

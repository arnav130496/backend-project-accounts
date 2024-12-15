package com.learning.accounts.feignclients;

import com.learning.accounts.dto.cards.CardsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CardsCircuitBreakerFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> getCard(String mobileNumber) {
        log.info("Fallback method executing for Cards");
        CardsDto cardsDto = new CardsDto();
        return new ResponseEntity<>(cardsDto, HttpStatus.PARTIAL_CONTENT);
    }
}

package com.learning.accounts.feignclients;

import com.learning.accounts.dto.cards.CardsDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CARDS", fallback = CardsCircuitBreakerFallback.class)
@LoadBalancerClient(name = "CARDS")
public interface CardsFeignClient {

    @GetMapping(path = "/api/v1/cards")
    public ResponseEntity<CardsDto> getCard(@RequestParam String mobileNumber);
}

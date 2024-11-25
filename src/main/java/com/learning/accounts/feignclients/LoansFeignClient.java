package com.learning.accounts.feignclients;

import com.learning.accounts.dto.loans.LoansDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LOANS")
@LoadBalancerClient(name = "LOANS")
public interface LoansFeignClient {

    @GetMapping(path = "/api/v1/loans")
    public ResponseEntity<LoansDto> getLoan(@RequestParam String mobileNumber);
}

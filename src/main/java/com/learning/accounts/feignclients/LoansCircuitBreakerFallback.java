package com.learning.accounts.feignclients;

import com.learning.accounts.dto.loans.LoansDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoansCircuitBreakerFallback implements LoansFeignClient {


    @Override
    public ResponseEntity<LoansDto> getLoan(String mobileNumber) {
        log.info("Fallback method executing for Loans");
        LoansDto loansDto = new LoansDto();
        return new ResponseEntity<>(loansDto, HttpStatus.PARTIAL_CONTENT);
    }
}

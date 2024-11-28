package com.ing.casestudy.controller;

import com.ing.casestudy.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/admin/loan")
@CrossOrigin
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/create")
    public ResponseEntity<?> createLoan(@RequestParam Long customerId,
                                        @RequestParam BigDecimal amount,
                                        @RequestParam BigDecimal interestRate,
                                        @RequestParam Short numberOfInstallments) {
        try {
            return ResponseEntity.ok(loanService.createLoan(customerId, amount, interestRate, numberOfInstallments));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<?> listLoansOfTheCustomer(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(loanService.findByCustomerId(customerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/list-paid/{customerId}")
    public ResponseEntity<?> listPaidLoansOfTheCustomer(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(loanService.findByCustomerIdAndIsPaid(customerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/pay-loan/{loanId}/{payAmount}")
    public ResponseEntity<?> payLoan(@PathVariable Long loanId,
                                     @PathVariable BigDecimal payAmount) {
        try {
            return ResponseEntity.ok(loanService.payLoan(loanId, payAmount));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}

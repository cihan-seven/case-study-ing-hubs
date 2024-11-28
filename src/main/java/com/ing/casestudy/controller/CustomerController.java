package com.ing.casestudy.controller;

import com.ing.casestudy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/find-loans-of-customer/{customerId}")
    public ResponseEntity<?> findLoansByCustomerId(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(customerService.findLoansByCustomerId(customerId));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/list-loan-installments-of-loan/{customerId}/{loanId}")
    public ResponseEntity<?> findLoanInstallmentsOfCustomerIdAndLoanId(@PathVariable Long customerId,
                                                                                    @PathVariable Long loanId) {
        try {
            return ResponseEntity.ok(customerService.findLoanInstallmentsOfCustomerIdAndLoanId(customerId, loanId));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(customerService.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}

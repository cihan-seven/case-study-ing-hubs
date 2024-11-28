package com.ing.casestudy.controller;

import com.ing.casestudy.service.LoanInstallmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/loan-installment")
@RequiredArgsConstructor
@CrossOrigin
public class LoanInstallmentController {

    private final LoanInstallmentService loanInstallmentService;

    @GetMapping("/list/{loanId}")
    public ResponseEntity<?> findByLoanId(@PathVariable Long loanId) {
        try {
            return ResponseEntity.ok(loanInstallmentService.findAllByLoanId(loanId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}

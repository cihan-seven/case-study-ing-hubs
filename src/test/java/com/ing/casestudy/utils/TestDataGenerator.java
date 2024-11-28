package com.ing.casestudy.utils;

import com.ing.casestudy.model.dto.CustomerDTO;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.model.entity.LoanInstallment;
import com.ing.casestudy.service.CustomerService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static Customer generateCustomer() {
        return Customer.builder()
                .name("Bill")
                .surname("Murray")
                .creditLimit(new BigDecimal("30000"))
                .usedCreditLimit(new BigDecimal("2000"))
                .build();
    }

    public static Loan generateLoan() {
        return Loan.builder()
                .loanAmount(new BigDecimal("6000"))
                .numberOfInstallment(Short.valueOf("6"))
                .paid(false)
                .createDate(LocalDate.of(2024, 7, 25))
                .build();
    }

    public static List<LoanInstallment> generateLoanInstallmentList() {
        List<LoanInstallment> loanInstallmentList = new ArrayList<>();

        LoanInstallment loanInstallment1 =
                LoanInstallment.builder()
                        .paymentDate(LocalDate.of(2024, 8, 1))
                        .paid(true)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(new BigDecimal("1000"))
                        .dueDate(LocalDate.of(2024, 8, 1))
                        .build();

        LoanInstallment loanInstallment2 =
                LoanInstallment.builder()
                        .paymentDate(LocalDate.of(2024, 9, 1))
                        .paid(true)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(new BigDecimal("1000"))
                        .dueDate(LocalDate.of(2024, 9, 1))
                        .build();

        LoanInstallment loanInstallment3 =
                LoanInstallment.builder()
                        .paymentDate(LocalDate.of(2024, 10, 1))
                        .paid(true)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(new BigDecimal("1000"))
                        .dueDate(LocalDate.of(2024, 10, 1))
                        .build();

        LoanInstallment loanInstallment4 =
                LoanInstallment.builder()
                        .paymentDate(LocalDate.of(2024, 11, 1))
                        .paid(true)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(new BigDecimal("1000"))
                        .dueDate(LocalDate.of(2024, 8, 1))
                        .build();

        LoanInstallment loanInstallment5 =
                LoanInstallment.builder()
                        .paymentDate(null)
                        .paid(false)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(null)
                        .dueDate(LocalDate.of(2024, 12, 1))
                        .build();

        LoanInstallment loanInstallment6 =
                LoanInstallment.builder()
                        .paymentDate(null)
                        .paid(false)
                        .amount(new BigDecimal("1000"))
                        .paidAmount(null)
                        .dueDate(LocalDate.of(2025, 1, 1))
                        .build();

        loanInstallmentList.add(loanInstallment1);
        loanInstallmentList.add(loanInstallment2);
        loanInstallmentList.add(loanInstallment3);
        loanInstallmentList.add(loanInstallment4);
        loanInstallmentList.add(loanInstallment5);
        loanInstallmentList.add(loanInstallment6);

        return loanInstallmentList;
    }

    public static Loan generateLoanWithLoanInstallmentList() {
        List<LoanInstallment> loanInstallmentList = generateLoanInstallmentList();

        Loan loan = generateLoan();
        loan.setLoanInstallmentList(loanInstallmentList);
        loan.setId(1L);

        return loan;
    }

    public static Customer generateCustomerWithLoan() {
        List<LoanInstallment> loanInstallmentList = generateLoanInstallmentList();

        Loan loan = generateLoan();
        loan.setLoanInstallmentList(loanInstallmentList);
        loan.setId(1L);

        Customer customer = generateCustomer();
        customer.setLoanList(List.of(loan));
        customer.setId(1L);

        return customer;
    }


    public static CustomerDTO generateCustomerDTO() {
        return CustomerService.convert(generateCustomer());
    }

    public static CustomerDTO generateCustomerDTOWithLoan() {
        return CustomerService.convert(generateCustomerWithLoan());
    }
}

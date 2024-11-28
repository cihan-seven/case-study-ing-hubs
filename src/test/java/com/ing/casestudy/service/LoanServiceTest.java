package com.ing.casestudy.service;

import com.ing.casestudy.model.dto.LoanDTO;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.repository.LoanRepository;
import com.ing.casestudy.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CustomerService customerService;

    private static final Long CUSTOMER_ID = 1L;

    @BeforeEach
    public void setup() {
        Customer mockCustomer = TestDataGenerator.generateCustomerWithLoan();
        Mockito.when(customerService.getById(CUSTOMER_ID)).thenReturn(mockCustomer);
        Mockito.when(loanRepository.save(any())).thenReturn(mockCustomer.getLoanList().get(0));
    }

    @Test
    public void testCreateLoan() {
        Long customerId = 1L;
        BigDecimal loanAmount = new BigDecimal("6000");
        BigDecimal interestRate = new  BigDecimal("0.2");
        Short numberOfInstallments = 6;

        LoanDTO loan = loanService.createLoan(customerId, loanAmount, interestRate, numberOfInstallments);

        assertNotNull(loan);
        assertEquals(new BigDecimal("6000"), loan.getLoanAmount());
        assertEquals(Short.valueOf("6"), loan.getNumberOfInstallment());
        assertEquals(1L, loan.getId());
        assertNotNull(loan.getLoanInstallmentList());
        assertEquals(6, loan.getLoanInstallmentList().size());

    }


}

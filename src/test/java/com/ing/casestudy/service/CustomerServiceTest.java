package com.ing.casestudy.service;

import com.ing.casestudy.model.dto.LoanDTO;
import com.ing.casestudy.model.dto.LoanInstallmentDTO;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.repository.CustomerRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private static final Long CUSTOMER_ID = 1L;

    @BeforeEach
    public void setup() {
        Customer mockCustomer = Customer.builder()
                .id(CUSTOMER_ID)
                .name("Akira")
                .surname("Kurasawa")
                .creditLimit(new BigDecimal("48000"))
                .usedCreditLimit(new BigDecimal("12000"))
                .build();

        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(mockCustomer));
    }

    @Test
    public void testGetCustomerById() {
        Customer result = customerService.getById(CUSTOMER_ID);

        assertNotNull(result);
        assertEquals(CUSTOMER_ID, result.getId());
        assertEquals("Akira", result.getName());
        assertEquals("Kurasawa", result.getSurname());
        assertEquals(new BigDecimal("48000"), result.getCreditLimit());
        assertEquals(new BigDecimal("12000"), result.getUsedCreditLimit());
    }

    @Test
    public void testFindLoansByCustomerId() {
        Long customerId = 1L;

        Customer mockCustomer = TestDataGenerator.generateCustomerWithLoan();

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));

        List<LoanDTO> loanList = customerService.findLoansByCustomerId(customerId);
        assertNotNull(loanList);
        assertEquals(1, mockCustomer.getLoanList().size());
        assertNotNull(mockCustomer.getLoanList());
        assertEquals(new BigDecimal("6000"), mockCustomer.getLoanList().get(0).getLoanAmount());
    }

    @Test
    public void testFindLoanInstallmentsOfCustomerIdAndLoanId() {
        Long customerId = 1L;
        Long loanId = 1L;

        Customer mockCustomer = TestDataGenerator.generateCustomerWithLoan();

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));

        List<LoanInstallmentDTO> loanInstallmentList = customerService.findLoanInstallmentsOfCustomerIdAndLoanId(customerId, loanId);
        assertNotNull(loanInstallmentList);
        assertEquals(6, loanInstallmentList.size());
        assertTrue(loanInstallmentList.get(0).isPaid());
    }

}
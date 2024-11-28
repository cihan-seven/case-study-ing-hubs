package com.ing.casestudy.service;

import com.ing.casestudy.model.dto.LoanInstallmentDTO;
import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.repository.LoanInstallmentRepository;
import com.ing.casestudy.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class LoanInstallmentServiceTest {

    @InjectMocks
    private LoanInstallmentService loanInstallmentService;

    @Mock
    private LoanInstallmentRepository loanInstallmentRepository;

    @Mock
    private LoanService loanService;

    private static final Long LOAN_ID = 1L;

    @BeforeEach
    public void setup() {
        Loan mockLoan = TestDataGenerator.generateLoanWithLoanInstallmentList();

        Mockito.when(loanService.getById(1L)).thenReturn(mockLoan);
    }

    @Test
    public void testFindAllByLoanId() {
        List<LoanInstallmentDTO> loanInstallmentList = loanInstallmentService.findAllByLoanId(LOAN_ID);

        assertNotNull(loanInstallmentList);
        assertEquals(6, loanInstallmentList.size());
        assertTrue(loanInstallmentList.get(0).isPaid());
    }

}

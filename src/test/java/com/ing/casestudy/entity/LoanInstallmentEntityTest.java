package com.ing.casestudy.entity;

import com.ing.casestudy.model.entity.LoanInstallment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanInstallmentEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private LoanInstallment loanInstallment;

    @Before
    public void setup() {
        loanInstallment = LoanInstallment.builder()
                .amount(new BigDecimal("4000"))
                .paidAmount(new BigDecimal("4000"))
                .dueDate(LocalDate.of(2024, 11, 1))
                .paid(true)
                .paymentDate(LocalDate.of(2024, 11, 1))
                .build();
    }

    @Test
    public void saveSocialMediaSiteFacebook() {
        LoanInstallment savedLoanInstallment = this.entityManager.persistAndFlush(loanInstallment);

        assertNotNull(savedLoanInstallment);
        assertThat(savedLoanInstallment.getAmount()).isEqualTo(new BigDecimal("4000"));
        assertThat(savedLoanInstallment.getDueDate()).isBefore(LocalDate.of(2024, 12,1));
        assertTrue(savedLoanInstallment.isPaid());
        assertThat(savedLoanInstallment.getPaymentDate()).isEqualTo(LocalDate.of(2024, 11, 1));
    }

}


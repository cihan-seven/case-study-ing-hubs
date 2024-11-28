package com.ing.casestudy.entity;

import com.ing.casestudy.model.entity.Loan;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private Loan loan;

    @Before
    public void setup() {
        loan = Loan.builder()
                .loanAmount(new BigDecimal("24000"))
                .createDate(LocalDate.of(2024,11,1))
                .numberOfInstallment(Short.valueOf("6"))
                .paid(false)
                .build();
    }

    @Test
    public void saveSocialMediaSiteFacebook() {
        Loan savedLoan = this.entityManager.persistAndFlush(loan);

        assertNotNull(savedLoan);
        assertThat(savedLoan.getLoanAmount()).isEqualTo(new BigDecimal("24000"));
        assertThat(savedLoan.getCreateDate()).isBefore(LocalDate.of(2024, 12,1));
        assertThat(savedLoan.getNumberOfInstallment()).isEqualTo(Short.valueOf("6"));
        assertFalse(savedLoan.isPaid());
    }

}

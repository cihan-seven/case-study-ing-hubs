package com.ing.casestudy.repository;


import com.ing.casestudy.exception.DatabaseObjectNotFoundError;
import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoanRepository loanRepository;

    @BeforeEach
    public void setUp() {
        Loan loan = TestDataGenerator.generateLoan();
        entityManager.persist(loan);
    }

    @Test
    public void whenFindById_thenReturnCustomer() {
        // given
        BigDecimal loanAmount = new BigDecimal("6000");
        Short numberOfInstallments = 6;
        boolean paid = false;
        LocalDate createDate = LocalDate.of(2024, 7, 25);

        // when
        Optional<Loan> foundOpt = loanRepository.findById(1L);
        Loan found = foundOpt.orElseThrow(() -> new DatabaseObjectNotFoundError("The loan with the given id could not be found!"));

        // then
        assertThat(found.getLoanAmount()).isEqualTo(loanAmount);
        assertThat(found.getNumberOfInstallment()).isEqualTo(numberOfInstallments);
        assertThat(found.isPaid()).isEqualTo(paid);
        assertThat(found.getCreateDate()).isEqualTo(createDate);
    }

    @Test
    public void whenFindAll_thenReturnCreatedLoan() {
        List<Loan> loanList = loanRepository.findAll();

        assertNotNull(loanList);
        assertEquals(1, loanList.size());
    }
}
package com.ing.casestudy.repository;


import com.ing.casestudy.model.entity.LoanInstallment;
import com.ing.casestudy.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class LoanInstallmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoanInstallmentRepository loanInstallmentRepository;

    @BeforeEach
    public void setUp() {
        List<LoanInstallment> loanInstallmentList = TestDataGenerator.generateLoanInstallmentList();

        loanInstallmentList
                        .forEach(li -> entityManager.persist(li));
    }

    @Test
    public void whenFindById_thenReturnCustomer() {
        // given
        Integer loanInstallmentListSize = 6;
        // ...

        // when
        List<LoanInstallment> foundList = loanInstallmentRepository.findAll();

        // then
        assertNotNull(foundList);
        assertThat(foundList.size()).isEqualTo(loanInstallmentListSize);
    }

}
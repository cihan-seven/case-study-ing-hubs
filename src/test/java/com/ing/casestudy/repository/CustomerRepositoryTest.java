package com.ing.casestudy.repository;

import com.ing.casestudy.exception.DatabaseObjectNotFoundError;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.utils.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        Customer customer = TestDataGenerator.generateCustomer();
        entityManager.persist(customer);
    }

    @Test
    public void whenFindById_thenReturnCustomer() {
        // given
        String name = "Bill";
        String surname = "Murray";
        BigDecimal creditLimit = new BigDecimal("30000");
        BigDecimal usedCreditLimit = new BigDecimal("2000");

        // when
        Optional<Customer> foundOpt = customerRepository.findById(1L);
        Customer found = foundOpt.orElseThrow(() -> new DatabaseObjectNotFoundError("The customer with the given id could not b e found!"));

        // then
        assertThat(found.getName()).isEqualTo(name);
        assertThat(found.getSurname()).isEqualTo(surname);
        assertThat(found.getCreditLimit()).isEqualTo(creditLimit);
        assertThat(found.getUsedCreditLimit()).isEqualTo(usedCreditLimit);
    }

    @Test
    public void whenFindByNonExistingId_thenReturnOptionalEmpty() {

        Optional<Customer> notExistingCustomer = customerRepository.findById(5L);

        assertEquals(Optional.empty(), notExistingCustomer);
    }
}
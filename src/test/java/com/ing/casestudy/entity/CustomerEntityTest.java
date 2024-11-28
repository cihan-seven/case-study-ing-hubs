package com.ing.casestudy.entity;

import com.ing.casestudy.model.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private Customer customer;

    @Before
    public void setup() {
        customer = Customer.builder()
                .name("Steve")
                .surname("Carell")
                .creditLimit(new BigDecimal("36000"))
                .usedCreditLimit(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void saveSocialMediaSiteFacebook() {
        Customer savedCustomer = this.entityManager.persistAndFlush(customer);

        assertNotNull(savedCustomer);
        assertThat(savedCustomer.getName()).isEqualTo("Steve");
        assertThat(savedCustomer.getSurname()).isEqualTo("Carell");
        assertThat(savedCustomer.getCreditLimit()).isGreaterThan(new BigDecimal("10000"));
        assertThat(savedCustomer.getUsedCreditLimit()).isLessThanOrEqualTo(BigDecimal.ZERO);
    }

}

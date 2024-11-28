package com.ing.casestudy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
//@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "credit_limit", precision = 12, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "used_credit_limit", precision = 12, scale = 2)
    private BigDecimal usedCreditLimit;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Loan> loanList;

}

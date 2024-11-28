package com.ing.casestudy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
//@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_amount", precision = 12, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "number_of_installment")
    private Short numberOfInstallment;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "is_paid")
    private boolean paid;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<LoanInstallment> loanInstallmentList;

    @ManyToOne
    private Customer customer;
}

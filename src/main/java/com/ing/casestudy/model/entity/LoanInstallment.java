package com.ing.casestudy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@Table(name = "loan_installment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "paid_amount", precision = 12, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "is_paid")
    private boolean paid;

    @ManyToOne
    private Loan loan;
}

package com.ing.casestudy.repository;

import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.model.entity.LoanInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {
}

package com.ing.casestudy.repository;

import com.ing.casestudy.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

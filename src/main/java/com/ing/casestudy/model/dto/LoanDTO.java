package com.ing.casestudy.model.dto;

import com.ing.casestudy.model.entity.LoanInstallment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {

    private Long id;
    private BigDecimal loanAmount;
    private Short numberOfInstallment;
    private LocalDate createDate;
    private boolean paid;
    private List<LoanInstallmentDTO> loanInstallmentList;

}

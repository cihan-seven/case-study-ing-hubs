package com.ing.casestudy.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayLoanResultDTO {

    private Integer numberOfPaidInstallments;
    private BigDecimal totalPaidAmount;
    private Boolean loanPaidCompletely;

}

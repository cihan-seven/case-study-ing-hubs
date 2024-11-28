package com.ing.casestudy.service;

import com.ing.casestudy.model.dto.LoanInstallmentDTO;
import com.ing.casestudy.model.entity.LoanInstallment;
import com.ing.casestudy.repository.LoanInstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanInstallmentService {

    private final LoanInstallmentRepository loanInstallmentRepository;
    private final LoanService loanService;

    public LoanInstallmentDTO save(LoanInstallmentDTO loanInstallmentDTO) {
        return convert(loanInstallmentRepository.save(convert(loanInstallmentDTO)));
    }

    public List<LoanInstallmentDTO> findAll() {
        return convertToDTOList(loanInstallmentRepository.findAll());
    }

    public List<LoanInstallmentDTO> findAllByLoanId(Long loanId) {
        List<LoanInstallmentDTO> dtoList = new ArrayList<>();
        List<LoanInstallment> list = loanService.getById(loanId).getLoanInstallmentList();
        list.forEach(li -> dtoList.add(convert(li)));

        return dtoList;
    }

    public static LoanInstallment convert(LoanInstallmentDTO dto) {
        return LoanInstallment.builder()
                .amount(dto.getAmount())
                .dueDate(dto.getDueDate())
                .paid(dto.isPaid())
                .paidAmount(dto.getPaidAmount())
                .paymentDate(dto.getPaymentDate())
                .build();
    }

    private List<LoanInstallmentDTO> convertToDTOList(List<LoanInstallment> list) {
        List<LoanInstallmentDTO> dtoList = new ArrayList<>();
        list.forEach(l -> dtoList.add(convert(l)));
        return dtoList;
    }

    private LoanInstallmentDTO convert(LoanInstallment li) {
        return LoanInstallmentDTO.builder()
                .id(li.getId())
                .paid(li.isPaid())
                .amount(li.getAmount())
                .paymentDate(li.getPaymentDate())
                .dueDate(li.getDueDate())
                .paidAmount(li.getPaidAmount())
                .build();
    }

}

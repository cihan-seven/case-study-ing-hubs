package com.ing.casestudy.service;

import com.ing.casestudy.exception.CustomerDoesNotOwnTheLoanException;
import com.ing.casestudy.exception.DatabaseObjectNotFoundError;
import com.ing.casestudy.model.dto.CustomerDTO;
import com.ing.casestudy.model.dto.LoanDTO;
import com.ing.casestudy.model.dto.LoanInstallmentDTO;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.repository.CustomerRepository;
import com.ing.casestudy.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public static final String COULD_NOT_BE_FOUND_ID = "Customer could not be found! id = ";

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;


    public List<CustomerDTO> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> dtoList = new ArrayList<>();

        customerList.forEach(c -> dtoList.add(convert(c)));

        return dtoList;
    }

    public CustomerDTO save(CustomerDTO dto) {
        return convert(customerRepository.save(convert(dto)));
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new DatabaseObjectNotFoundError(COULD_NOT_BE_FOUND_ID + id));
    }

    public CustomerDTO findById(Long id) {
        return convert(customerRepository.findById(id)
                .orElseThrow(() -> new DatabaseObjectNotFoundError(COULD_NOT_BE_FOUND_ID + id)));
    }

    public List<LoanDTO> findLoansByCustomerId(Long customerId) {
        return findById(customerId).getLoanList();
    }

    public List<LoanInstallmentDTO> findLoanInstallmentsOfCustomerIdAndLoanId(Long customerId, Long loanId) {
        CustomerDTO customerDTO = convert(getById(customerId));
        List<LoanDTO> filtered = customerDTO.getLoanList().stream()
                .filter(loan -> Objects.equals(loan.getId(), loanId))
                .toList();

        if (filtered.isEmpty())
            throw new CustomerDoesNotOwnTheLoanException("This loan does not belong to this customer! ");

        return filtered.get(0).getLoanInstallmentList();
    }

    public static CustomerDTO convert(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .creditLimit(customer.getCreditLimit())
                .usedCreditLimit(customer.getUsedCreditLimit())
                .name(customer.getName())
                .surname(customer.getSurname())
                .loanList(LoanService.convertToLoanDTOList(customer.getLoanList()))
                .build();
    }


    public Customer convert(CustomerDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .creditLimit(dto.getCreditLimit())
                .usedCreditLimit(dto.getUsedCreditLimit())
                .loanList(Objects.isNull(dto.getLoanList()) ? null : convert(dto.getLoanList()))
                .build();
    }

    private List<Loan> convert(List<LoanDTO> dtoList) {
        List<Loan> loanList = new ArrayList<>();
        dtoList.forEach(
                dto -> loanList.add(LoanService.convert(dto)));
        return loanList;
    }

}

package com.ing.casestudy.service;

import com.ing.casestudy.exception.*;
import com.ing.casestudy.model.dto.LoanDTO;
import com.ing.casestudy.model.dto.LoanInstallmentDTO;
import com.ing.casestudy.model.dto.PayLoanResultDTO;
import com.ing.casestudy.model.entity.Customer;
import com.ing.casestudy.model.entity.Loan;
import com.ing.casestudy.model.entity.LoanInstallment;
import com.ing.casestudy.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class LoanService {

    public static final String COULD_NOT_BE_FOUND_ID = "The loan could not be found! id = ";
    public static final String INSUFFICIENT_LIMIT_ERROR = "Customer has insufficient limit for this credit amount!";
    public static final String NUMBER_OF_INSTALLMENTS_ERROR = "Number of installments can only be 6, 9, 12 ,24!";
    public static final String NOT_IN_RANGE_ERROR = "Interest rate can be between 0.1-0.5";

    private static final List<Integer> NUMBER_OF_INSTALLMENTS = List.of(6, 9, 12, 24);
    public static final String IS_INSUFFICIENT = "The proposed amount to pay the loan is insufficient!";
    public static final String ALREADY_BEEN_PAID = "This loan has already been paid!";


    private final LoanRepository loanRepository;
    private final CustomerService customerService;

//    public LoanDTO save(LoanDTO loanDTO) {
//        return convert(loanRepository.save(convert(loanDTO)));
//    }
//
//    public List<LoanDTO> findAll() {
//        return convertToLoanDTOList(loanRepository.findAll());
//    }

    public Loan getById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new DatabaseObjectNotFoundError(COULD_NOT_BE_FOUND_ID + loanId));
    }

//    public LoanDTO findById(Long loanId) {
//        return convert(loanRepository.findById(loanId)
//                .orElseThrow(() -> new DatabaseObjectNotFoundError(COULD_NOT_BE_FOUND_ID + loanId)));
//    }

    public List<LoanDTO> findByCustomerId(Long customerId) {
        return customerService.findById(customerId).getLoanList();
    }

    public List<LoanDTO> findByCustomerIdAndIsPaid(Long customerId){
        List<LoanDTO> loanDTOList = findByCustomerId(customerId);

        return loanDTOList.stream()
                .filter(LoanDTO::isPaid)
                .toList();
    }


    public LoanDTO createLoan(Long customerId, BigDecimal amount, BigDecimal interestRate, Short numberOfInstallments) {
        Customer customer = customerService.getById(customerId);

        BigDecimal availableLimit = customer.getCreditLimit().subtract(customer.getUsedCreditLimit());

        /** Checking whether the credit application is valid */
        checkAvailabilityOfTheCreditApplication(customer, amount, interestRate, numberOfInstallments, availableLimit);

        BigDecimal totalAmount = amount.multiply(BigDecimal.ONE.add(interestRate));
        BigDecimal averageInstallmentAmount = totalAmount.divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.UP);

        LocalDate firstDueDate = LocalDate.now();  /** Loan application happens today */
        List<LoanInstallmentDTO> loanInstallmentDTOList = createInstallments(firstDueDate, averageInstallmentAmount, numberOfInstallments);

        Loan loan = new Loan();
        loan.setLoanAmount(totalAmount);
        loan.setCreateDate(LocalDate.now());
        loan.setPaid(false);
        loan.setNumberOfInstallment(numberOfInstallments);
        loan.setCustomer(customer);
        loan.setLoanInstallmentList(convert(loanInstallmentDTOList));

        return convert(loanRepository.save(loan));
    }

    public PayLoanResultDTO payLoan(Long loanId, BigDecimal payAmount) {
        Loan loan = getById(loanId);

        if (loan.isPaid())
            throw new LoanAlreadyPaidException(ALREADY_BEEN_PAID);

        if (payAmount.compareTo(loan.getLoanInstallmentList().get(0).getAmount()) < 0)
            throw new InsufficientLoanAmountException(IS_INSUFFICIENT);

        return distributePayAmount(loan, payAmount);
    }


    private PayLoanResultDTO distributePayAmount(Loan loan, BigDecimal payAmount) {
        List<LoanInstallment> loanInstallmentList = loan.getLoanInstallmentList();

        BigDecimal amountPerInstallment = loanInstallmentList.get(0).getAmount();
        BigDecimal leftAmount = payAmount;

        int numberOfInstallmentsCounter = loan.getNumberOfInstallment();

        int paidCounter = 0;

        while(leftAmount.compareTo(amountPerInstallment) >= 0 && numberOfInstallmentsCounter > 0 && paidCounter < 3) {

            int current = loan.getNumberOfInstallment() - numberOfInstallmentsCounter;

            if (!loanInstallmentList.get(current).isPaid()) {

                /** BONUS -2 */
                BigDecimal amountToPayWithRewardOrPenalty =
                        calculateTheAmountToPayRegardingPaymentDate(amountPerInstallment, loanInstallmentList.get(current).getDueDate());

                loanInstallmentList.get(current).setPaidAmount(amountToPayWithRewardOrPenalty);
                loanInstallmentList.get(current).setPaymentDate(LocalDate.now());
                loanInstallmentList.get(current).setPaid(true);

                leftAmount = leftAmount.subtract(amountToPayWithRewardOrPenalty);
                paidCounter++;
            }

            numberOfInstallmentsCounter--;

        }

        loan.setLoanInstallmentList(loanInstallmentList);
        loan.setPaid(checkLoanIsPaid(loan));

        loanRepository.save(loan);

        return PayLoanResultDTO.builder()
                .numberOfPaidInstallments(paidCounter)
                .totalPaidAmount(payAmount.subtract(leftAmount))
                .loanPaidCompletely(checkLoanIsPaid(loan))
                .build();
    }

/**     BONUS-2         */
//    - If an installment is paid before due date, make a discount equal to
//       0.001*(number of days before due date) So in this case paidAmount of
//       installment will be lower than amount.
//    - If an installment is paid after due date, add a penalty equal to 0.001*(number
//       of days after due date) So in this case paidAmount of installment will be
//       higher than amount
    private BigDecimal calculateTheAmountToPayRegardingPaymentDate(BigDecimal fixedAmount, LocalDate dueDate) {
        long daysBetween = DAYS.between(LocalDate.now(), dueDate); // positive if dueDate is later

        BigDecimal rewardOrPenaltyAmount = new BigDecimal(daysBetween).multiply(new BigDecimal("0.001"));

        return daysBetween < 0
                ? fixedAmount.add(rewardOrPenaltyAmount)
                : fixedAmount.subtract(rewardOrPenaltyAmount);
    }

    private boolean checkLoanIsPaid(Loan loan) {
        return loan.getLoanInstallmentList()
                .stream()
                .filter(LoanInstallment::isPaid)
                .toList()
                .size()
                == loan.getLoanInstallmentList().size();
    }

    private List<LoanInstallmentDTO> createInstallments(LocalDate firstDueDate, BigDecimal amount, Short numberOfInstallments) {
        List<LoanInstallmentDTO> dtoList = new ArrayList<>();

        LocalDate firstFixedDueDate = findTheNextFirstDayOfTheUpcomingMonth(firstDueDate);
        LoanInstallmentDTO dto = createInstallment(firstFixedDueDate, amount);

        dtoList.add(dto);

        for (int i = 1; i < numberOfInstallments; i++) {
            LocalDate fixedNextdate = findTheNextFirstDayOfTheUpcomingMonth(dtoList.get(i-1).getDueDate());
            dtoList.add(createInstallment(fixedNextdate, amount));
        }

        return dtoList;
    }

    private LoanInstallmentDTO createInstallment(LocalDate dueDate, BigDecimal amount) {
        return LoanInstallmentDTO.builder()
                .amount(amount)
                .dueDate(dueDate)
                .paidAmount(null)
                .paid(false)
                .paymentDate(null)
                .build();
    }

    private LocalDate findTheNextFirstDayOfTheUpcomingMonth(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();

        if (dayOfMonth != 1)
            dayOfMonth = 1;

        if (date.getMonthValue() == 12) {
            month = 1;
            year = date.getYear() + 1;
            return LocalDate.of(year, month, dayOfMonth);
        }

        return LocalDate.of(year, month + 1, dayOfMonth);

    }

    private void checkAvailabilityOfTheCreditApplication(Customer customer, BigDecimal amount, BigDecimal interestRate,
                                                         Short numberOfInstallments, BigDecimal availableLimit)
            throws CustomerLimitExceedError, NumberOfInstallmentsMatchError, InterestRateNotInRangeError {

        if (availableLimit.compareTo(amount) < 0)
            throw new CustomerLimitExceedError(INSUFFICIENT_LIMIT_ERROR);

        if (!NUMBER_OF_INSTALLMENTS.contains(Integer.parseInt(numberOfInstallments.toString())))
            throw new NumberOfInstallmentsMatchError(NUMBER_OF_INSTALLMENTS_ERROR);

        if (interestRate.compareTo(new BigDecimal("0.1")) < 0 || interestRate.compareTo(new BigDecimal("0.5")) > 0)
            throw new InterestRateNotInRangeError(NOT_IN_RANGE_ERROR);

    }

    public static List<LoanDTO> convertToLoanDTOList(List<Loan> list) {
        List<LoanDTO> dtoList = new ArrayList<>();
        list.forEach(l -> dtoList.add(convert(l)));

        return  dtoList;
    }

    public static Loan convert(LoanDTO dto) {
        return Loan.builder()
                .createDate(LocalDate.now())
                .loanAmount(dto.getLoanAmount())
                .paid(dto.isPaid())
                .numberOfInstallment(dto.getNumberOfInstallment())
                .loanInstallmentList(convert(dto.getLoanInstallmentList()))
                .build();
    }

    public static LoanInstallment convert(LoanInstallmentDTO dto) {
        return LoanInstallmentService.convert(dto);
    }

    public static List<LoanInstallment> convert(List<LoanInstallmentDTO> dtoList) {
        List<LoanInstallment> loanInstallmentList = new ArrayList<>();
        dtoList.forEach(
                dto -> loanInstallmentList.add(convert(dto)));
        return loanInstallmentList;
    }

    private static LoanDTO convert(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .paid(loan.isPaid())
                .numberOfInstallment(loan.getNumberOfInstallment())
                .loanAmount(loan.getLoanAmount())
                .createDate(loan.getCreateDate())
                .loanInstallmentList(convertToDTOList(loan.getLoanInstallmentList()))
                .build();
    }

    private static List<LoanInstallmentDTO> convertToDTOList(List<LoanInstallment> list) {
        List<LoanInstallmentDTO> dtoList = new ArrayList<>();
        list.forEach(li -> dtoList.add(convert(li)));
        return dtoList;
    }

    private static LoanInstallmentDTO convert(LoanInstallment loanInstallment) {
        return LoanInstallmentDTO.builder()
                .id(loanInstallment.getId())
                .amount(loanInstallment.getAmount())
                .paidAmount(loanInstallment.getPaidAmount())
                .dueDate(loanInstallment.getDueDate())
                .paymentDate(loanInstallment.getPaymentDate())
                .paid(loanInstallment.isPaid())
                .build();
    }

}

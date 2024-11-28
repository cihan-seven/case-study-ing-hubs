-- Table for Customer
CREATE TABLE customer (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      surname VARCHAR(255) NOT NULL,
      credit_limit DECIMAL(12, 2),
      used_credit_limit DECIMAL(12, 2)
);

-- Table for Loan
CREATE TABLE loan (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      loan_amount DECIMAL(12, 2),
      number_of_installment SMALLINT,
      create_date DATE,
      is_paid BOOLEAN,
      customer_id BIGINT,
      CONSTRAINT fk_loan_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Table for LoanInstallment
CREATE TABLE loan_installment (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      amount DECIMAL(12, 2),
      paid_amount DECIMAL(12, 2),
      due_date DATE,
      payment_date DATE,
      is_paid BOOLEAN,
      loan_id BIGINT,
      CONSTRAINT fk_loan_installment_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE
);

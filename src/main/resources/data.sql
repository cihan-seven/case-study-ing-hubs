INSERT INTO Customer (id, name, surname, credit_limit, used_credit_limit) VALUES (1, 'Henry', 'Fonda', 40000, 12000);
INSERT INTO Customer (id, name, surname, credit_limit, used_credit_limit) VALUES (2, 'Clint', 'Eastwood', 30000, 18000);

INSERT INTO Loan (id, loan_amount, number_of_installment, create_date, is_paid, customer_id) VALUES (1, 3000, 6, '2024-01-05', true, 1);
INSERT INTO Loan (id, loan_amount, number_of_installment, create_date, is_paid, customer_id) VALUES (2, 12000, 12, '2024-06-18', false, 1);
INSERT INTO Loan (id, loan_amount, number_of_installment, create_date, is_paid, customer_id) VALUES (3, 18000, 9, '2024-10-12', false, 2);


-- Loan No1 : Customer No1 - the paid one
-- 3000/6 = 500 for each payment
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (1, 500, 500, '2024-02-01', '2024-02-01', true, 1);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (2, 500, 500, '2024-03-01', '2024-03-01', true, 1);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (3, 500, 500, '2024-04-01', '2024-04-01', true, 1);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (4, 500, 500, '2024-05-01', '2024-05-01', true, 1);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (5, 500, 500, '2024-06-01', '2024-06-01', true, 1);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (6, 500, 500, '2024-07-01', '2024-07-01', true, 1);

-- Loan No2 : Customer No1 - the active loan
-- 12000/12 = 1000 for each payment
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (7, 1000, 1000, '2024-07-01', '2024-07-01', true, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (8, 1000, 1000, '2024-08-01', '2024-08-01', true, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (9, 1000, 1000, '2024-09-01', '2024-09-01', true, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (10, 1000, 1000, '2024-10-01', '2024-10-01', true, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (11, 1000, 1000, '2024-11-01', '2024-11-01', true, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (12, 1000, null, '2024-12-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (13, 1000, null, '2025-01-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (14, 1000, null, '2025-02-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (15, 1000, null, '2025-03-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (16, 1000, null, '2025-04-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (17, 1000, null, '2025-05-01', null, false, 2);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (18, 1000, null, '2025-06-01', null, false, 2);

-- Loan No3 : Customer No2 - the active loan
-- 18000/9 = 2000 for each payment
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (19, 2000, 2000, '2024-11-01', '2024-11-01', true, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (20, 2000, null, '2024-12-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (21, 2000, null, '2025-01-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (22, 2000, null, '2025-02-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (23, 2000, null, '2025-03-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (24, 2000, null, '2025-04-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (25, 2000, null, '2025-05-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (26, 2000, null, '2025-06-01', null, false, 3);
INSERT INTO loan_installment (id, amount, paid_amount, due_date, payment_date, is_paid, loan_id) VALUES  (27, 2000, null, '2025-07-01', null, false, 3);


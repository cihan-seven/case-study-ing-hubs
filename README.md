README

When the application is started, the schema.sql file runs and creates Customer, Loan nad LoanInstallment DB tables. 
And after creation of the tables, data.sql runs and inserts dummy data to the H2 DB.


Also, after initialization of the application "customer" and "admin" users (with the respective passwords "customer", "admin") are created 
for filtering requests. 

user: customer password: customer role: CUSTOMER
user: admin password: admin role: ADMIN


-- The predefined data for the available credit-limit in H2 (Customer-Loan scenario)
Customers : There are two mockup customers : Henry and Clint
Loans: There are 3 loans (2 for Henry  and 1 for Clint) Henry has a paid loan and a loan that is still being paid. Clint has an ongoing loan too.
LoanInstallments: The LoanInstallment datas for the Loans mentioned above.



HOW TO SEND REQUESTS?
Basic Authentication is needed to send request. 
When sending request to the endpoints Basic Auth (username, password) information should be attached to the request.

e.g:
GET http://localhost:8090/customer/find-all
Authorization: Basic customer customer

or,

GET http://localhost:8090/customer/find-all
Authorization: Basic admin admin


EXTRAS INFORMATION ABOUT THE APPLICATION LOGIC:
The used_credit_limit is designed to be calculated only when there is an active loan that is still being paid.
The total amount of the loan will be subtracted from the credit_limit regarding the risk of never being paid back.
Since there is no attribute anywhere in between three entities, the interest rate will be supposed to have a value of 0.
Thus, the LoanInstallment records will have the equal exact division value of the total credit amount, without any interest on top of it.


***************************************************************************************************************************************************************
2024-11-29
Cihan Adil Seven
***************************************************************************************************************************************************************
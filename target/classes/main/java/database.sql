CREATE DATABASE atm;

USE atm;

CREATE TABLE accounts (
                          accountNumber INT AUTO_INCREMENT PRIMARY KEY,
                          holderName VARCHAR(255),
                          balance DOUBLE,
                          status VARCHAR(50),
                          login VARCHAR(50) UNIQUE,
                          pin INT
);


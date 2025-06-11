CREATE DATABASE bank_system;

USE bank_system;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    account_type VARCHAR(20),
    balance DOUBLE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
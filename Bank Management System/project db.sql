CREATE DATABASE BankDB;
USE BankDB;
-- TABLE
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
INSERT INTO users (username, password) VALUES
('admin', 'admin123'),
('user1', 'pass1');


-- TABLE
CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15)
);
INSERT INTO client (name, phone) VALUES
('Ali Khan', '03001234567'),
('Sara Ahmed', '03007654321');

-- TABLE: 
CREATE TABLE  account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);
INSERT INTO account (client_id, balance) VALUES
(1, 5000),
(2, 10000);

-- TABLE
DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
    tx_id INT AUTO_INCREMENT PRIMARY KEY,
    acc_id INT,
    amount DOUBLE,
    type VARCHAR(20),
    tx_date DATE
);


-- STORED PROCEDURE
DELIMITER //
CREATE PROCEDURE addTransaction(
    IN p_account_id INT,
    IN p_amount DECIMAL(15,2),
    IN p_type VARCHAR(20)
)
BEGIN
    INSERT INTO transaction(account_id, amount, type)
    VALUES (p_account_id, p_amount, p_type);
END //
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE deposit_money(
    IN acc INT,
    IN amt DOUBLE
)
BEGIN
    UPDATE account
    SET balance = balance + amt
    WHERE id = acc;

    INSERT INTO transactions(acc_id,amount,type)
    VALUES(acc, amt, 'Deposit');
END$$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE withdraw_money(
    IN acc INT,
    IN amt DOUBLE
)
BEGIN
    UPDATE account
    SET balance = balance - amt
    WHERE id = acc;

    INSERT INTO transactions(acc_id,amount,type)
    VALUES(acc, amt, 'Withdraw');
END$$

DELIMITER ;


-- TRIGGER
DELIMITER //
CREATE TRIGGER after_transaction_insert
AFTER INSERT ON transaction
FOR EACH ROW
BEGIN
    IF NEW.type = 'Deposit' THEN
        UPDATE account SET balance = balance + NEW.amount WHERE id = NEW.account_id;
    ELSEIF NEW.type = 'Withdraw' THEN
        UPDATE account SET balance = balance - NEW.amount WHERE id = NEW.account_id;
    END IF;
END //
DELIMITER ;

DELIMITER $$

CREATE TRIGGER check_balance
BEFORE UPDATE ON account
FOR EACH ROW
BEGIN
    IF NEW.balance < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient Balance';
    END IF;
END$$

DELIMITER ;

-- VIEW
CREATE VIEW clientAccountsView AS
SELECT 
    c.id AS client_id,
    c.name,
    c.phone,
    a.id AS account_id,
    a.balance
FROM client c
JOIN account a ON c.id = a.client_id;


-- Check clients and accounts
SELECT * FROM clientAccountsView;

-- Check transactions
SELECT * FROM transaction;

select* from users;

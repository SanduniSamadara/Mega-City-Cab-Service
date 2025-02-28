CREATE DATABASE IF NOT EXISTS megacitycab;
USE megacitycab;

CREATE TABLE IF NOT EXISTS user (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,  -- Storing as plain text for now (use hashing in production)
    role ENUM('admin', 'driver', 'customer') NOT NULL
    );

INSERT INTO user (username, password, role) VALUES
                                                ('admin123', 'password123', 'admin'),
                                                ('driver01', 'driverpass', 'driver');

CREATE TABLE IF NOT EXISTS customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           registration_number VARCHAR(255) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           address TEXT NOT NULL,
                           nic VARCHAR(255) NOT NULL
);

INSERT INTO customers (registration_number, name, address, nic)
VALUES
    ('R001', 'John Doe', '123 Main St, Colombo, Sri Lanka', '123456789V'),
    ('R002', 'Jane Smith', '456 Elm St, Kandy, Sri Lanka', '987654321V'),
    ('R003', 'Michael Brown', '789 Pine St, Galle, Sri Lanka', '112233445V'),
    ('R004', 'Emily White', '321 Oak St, Jaffna, Sri Lanka', '556677889V'),
    ('R005', 'David Black', '654 Maple St, Negombo, Sri Lanka', '223344556V');

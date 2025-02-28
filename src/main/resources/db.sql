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

CREATE TABLE cars (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      plate_number VARCHAR(50) NOT NULL,
                      year INT NOT NULL,
                      price DOUBLE NOT NULL,
                      model VARCHAR(50) NOT NULL
);

INSERT INTO cars (name, plate_number, year, price, model) VALUES
                                                              ('Toyota Camry', 'ABC123', 2018, 22000.00, 'Camry'),
                                                              ('Honda Civic', 'XYZ789', 2019, 20000.00, 'Civic'),
                                                              ('Ford Focus', 'FOC456', 2017, 18000.00, 'Focus'),
                                                              ('Chevrolet Malibu', 'CHEV111', 2020, 25000.00, 'Malibu'),
                                                              ('Nissan Altima', 'ALT222', 2021, 27000.00, 'Altima');
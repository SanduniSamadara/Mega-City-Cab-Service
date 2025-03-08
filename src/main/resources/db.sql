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

CREATE TABLE drivers (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         license_number VARCHAR(255) NOT NULL,
                         address TEXT NOT NULL,
                         contact_number VARCHAR(255) NOT NULL
);

INSERT INTO drivers (name, license_number, address, contact_number)
VALUES
    ('John Doe', 'ABC12345', '123 Street, Colombo', '0771234567'),
    ('Jane Smith', 'XYZ67890', '456 Avenue, Colombo', '0782345678'),
    ('Mark Johnson', 'LMN11223', '789 Road, Kandy', '0773456789'),
    ('Emily Davis', 'QRS44556', '1010 Lane, Galle', '0784567890'),
    ('Michael Brown', 'TUV99887', '2020 Boulevard, Negombo', '0775678901');


CREATE TABLE bookings (
                          booking_number VARCHAR(50) PRIMARY KEY,
                          customer_name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          telephone VARCHAR(50) NOT NULL,
                          destination VARCHAR(255) NOT NULL
);

INSERT INTO bookings (booking_number, customer_name, address, telephone, destination) VALUES
                                                                                          ('B001', 'John Doe', '123 Main St, Colombo', '0711234567', 'Galle'),
                                                                                          ('B002', 'Jane Smith', '456 Park Rd, Colombo', '0779876543', 'Kandy'),
                                                                                          ('B003', 'Alice Fernando', '789 Ocean View, Colombo', '0765432100', 'Negombo'),
                                                                                          ('B004', 'Bob Perera', '321 Hill St, Colombo', '0781234567', 'Anuradhapura'),
                                                                                          ('B005', 'Carol De Silva', '654 River Rd, Colombo', '0759876543', 'Matale');

INSERT INTO cars (name, plate_number, year, price, model) VALUES
                                                              ('Toyota Camry', 'ABC123', 2018, 22000.00, 'Camry'),
                                                              ('Honda Civic', 'XYZ789', 2019, 20000.00, 'Civic'),
                                                              ('Ford Focus', 'FOC456', 2017, 18000.00, 'Focus'),
                                                              ('Chevrolet Malibu', 'CHEV111', 2020, 25000.00, 'Malibu'),
                                                              ('Nissan Altima', 'ALT222', 2021, 27000.00, 'Altima');

CREATE TABLE car_booking (
                          booking_number VARCHAR(50) PRIMARY KEY,
                          customer_name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          telephone VARCHAR(50) NOT NULL,
                          destination_from VARCHAR(255) NOT NULL,
                          destination_to VARCHAR(255) NOT NULL,
                          distance DOUBLE NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO car_booking (booking_number, customer_name, address, telephone, destination_from, destination_to) VALUES
                                                                                          ('B001', 'John Doe', '123 Main St, Colombo', '0711234567', 'Galle', 'Matara'),
                                                                                          ('B002', 'Jane Smith', '456 Park Rd, Colombo', '0779876543', 'Kandy', 'Kurunegala');


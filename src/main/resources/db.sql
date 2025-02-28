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


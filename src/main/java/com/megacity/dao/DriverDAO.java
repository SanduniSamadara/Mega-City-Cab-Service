package com.megacity.dao;

import com.megacity.model.Driver;
import java.util.List;

public interface DriverDAO {
    void addDriver(Driver driver);
    Driver getDriverById(int id);
    List<Driver> getAllDrivers();
    void updateDriver(Driver driver);
    void deleteDriver(int id);
}
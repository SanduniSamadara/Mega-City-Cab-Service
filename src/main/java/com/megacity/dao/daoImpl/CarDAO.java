package com.megacity.dao.daoImpl;

import com.megacity.model.Car;

import java.util.List;

public interface CarDAO {
    void addCar(Car car);
    Car getCarById(int id);
    List<Car> getAllCars();
    void updateCar(Car car);
    void deleteCar(int id);
}

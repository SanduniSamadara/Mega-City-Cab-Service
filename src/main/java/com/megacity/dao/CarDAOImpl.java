package com.megacity.dao;

import com.megacity.dao.daoImpl.CarDAO;
import com.megacity.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/megacitycab";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Add a new car
    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO cars (name, plate_number, year, price, model) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getPlateNumber());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPrice());
            stmt.setString(5, car.getModel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        String sql = "SELECT * FROM cars WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                car = new Car(rs.getInt("id"), rs.getString("name"), rs.getString("plate_number"), rs.getInt("year"), rs.getDouble("price"), rs.getString("model"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
        public List<Car> getAllCars() {
            List<Car> cars = new ArrayList<>();
            String sql = "SELECT * FROM cars";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    cars.add(new Car(rs.getInt("id"), rs.getString("name"), rs.getString("plate_number"), rs.getInt("year"), rs.getDouble("price"), rs.getString("model")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cars;
        }

    @Override
    public void updateCar(Car car) {
        String sql = "UPDATE cars SET name=?, plate_number=?, year=?, price=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getPlateNumber());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPrice());
            stmt.setInt(5, car.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
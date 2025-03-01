package com.megacity.dao.daoImpl;

import com.megacity.dao.DriverDAO;
import com.megacity.model.Driver;
import com.megacity.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    private static final String ADD_DRIVER_SQL = "INSERT INTO drivers (license_number, name, address, contact_number) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_DRIVER_SQL = "UPDATE drivers SET license_number = ?, name = ?, address = ?, contact_number = ? WHERE id = ?";
    private static final String DELETE_DRIVER_SQL = "DELETE FROM drivers WHERE id = ?";
    private static final String GET_ALL_DRIVERS_SQL = "SELECT * FROM drivers";
    private static final String GET_DRIVER_BY_ID_SQL = "SELECT * FROM drivers WHERE id = ?";

    @Override
    public void addDriver(Driver driver) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DRIVER_SQL)) {
            preparedStatement.setString(1, driver.getLicenseNumber());
            preparedStatement.setString(2, driver.getName());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setString(4, driver.getContactNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDriver(Driver driver) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRIVER_SQL)) {
            preparedStatement.setString(1, driver.getLicenseNumber());
            preparedStatement.setString(2, driver.getName());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setString(4, driver.getContactNumber());
            preparedStatement.setInt(5, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDriver(int customerId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRIVER_SQL)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DRIVERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String registrationNumber = resultSet.getString("license_number");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String contact_number = resultSet.getString("contact_number");
                drivers.add(new Driver(id, registrationNumber, name, address, contact_number));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public Driver getDriverById(int driverId) {
        Driver driver = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DRIVER_BY_ID_SQL)) {
            preparedStatement.setInt(1, driverId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String registrationNumber = resultSet.getString("license_number");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String contact_number = resultSet.getString("contact_number");
                    driver = new Driver(id, registrationNumber, name, address, contact_number);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return driver;
    }

}
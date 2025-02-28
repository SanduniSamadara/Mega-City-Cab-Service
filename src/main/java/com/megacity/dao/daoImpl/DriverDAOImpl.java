package com.megacity.dao.daoImpl;

import com.megacity.dao.DriverDAO;
import com.megacity.model.Driver;
import com.megacity.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    private Connection connection;

    public DriverDAOImpl() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addDriver(Driver driver) {
        String query = "INSERT INTO drivers (name, license_number, address, contact_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getAddress());
            stmt.setString(4, driver.getContactNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Driver getDriverById(int id) {
        String query = "SELECT * FROM drivers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Driver(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("license_number"),
                        resultSet.getString("address"),
                        resultSet.getString("contact_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                drivers.add(new Driver(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("license_number"),
                        resultSet.getString("address"),
                        resultSet.getString("contact_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public void updateDriver(Driver driver) {
        String query = "UPDATE drivers SET name = ?, license_number = ?, address = ?, contact_number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getAddress());
            stmt.setString(4, driver.getContactNumber());
            stmt.setInt(5, driver.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDriver(int id) {
        String query = "DELETE FROM drivers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

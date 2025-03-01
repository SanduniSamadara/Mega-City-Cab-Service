package com.megacity.dao.daoImpl;

import com.megacity.dao.CustomerDAO;
import com.megacity.model.Customer;
import com.megacity.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String ADD_CUSTOMER_SQL = "INSERT INTO customers (registration_number, name, address, nic) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customers SET registration_number = ?, name = ?, address = ?, nic = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customers WHERE id = ?";
    private static final String GET_ALL_CUSTOMERS_SQL = "SELECT * FROM customers";
    private static final String GET_CUSTOMER_BY_ID_SQL = "SELECT * FROM customers WHERE id = ?";

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getRegistrationNumber());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getNic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getRegistrationNumber());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getNic());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String registrationNumber = resultSet.getString("registration_number");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String nic = resultSet.getString("nic");
                customers.add(new Customer(id, registrationNumber, name, address, nic));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_ID_SQL)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String registrationNumber = resultSet.getString("registration_number");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String nic = resultSet.getString("nic");
                    customer = new Customer(id, registrationNumber, name, address, nic);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
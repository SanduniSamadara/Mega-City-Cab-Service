package com.megacity.dao;

import com.megacity.model.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private Connection connection;

    // Constructor: Set up the database connection
    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public Admin getAdminById(int adminId) {
        String sql = "SELECT * FROM user WHERE id = ?";
        Admin admin = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    @Override
    public void deleteAdmin(int adminId) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE user SET username = ?, password = ?, role = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getRole());
            preparedStatement.setInt(4, admin.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

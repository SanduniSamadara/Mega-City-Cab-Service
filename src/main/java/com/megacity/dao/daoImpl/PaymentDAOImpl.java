package com.megacity.dao.daoImpl;

import com.megacity.dao.PaymentDAO;
import com.megacity.model.Payment;
import com.megacity.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    private static final String INSERT_PAYMENT_SQL = "INSERT INTO payment (booking_number, amount, payment_method, payment_status, payment_date) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addPayment(Payment payment) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_SQL)) {

            preparedStatement.setString(1, payment.getOrderNumber());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getStatus());
            preparedStatement.setString(5, payment.getPaymentDate());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

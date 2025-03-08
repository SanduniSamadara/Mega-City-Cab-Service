package com.megacity.dao;

import com.megacity.model.Payment;

import java.sql.SQLException;

public interface PaymentDAO {

    void addPayment(Payment payment) throws SQLException;
}

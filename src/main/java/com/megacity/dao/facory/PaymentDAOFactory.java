package com.megacity.dao.facory;

import com.megacity.dao.PaymentDAO;
import com.megacity.dao.daoImpl.PaymentDAOImpl;

public class PaymentDAOFactory {
    public static PaymentDAO getPaymentDAO() {
        return new PaymentDAOImpl();  // Return an instance of the implementation
    }
}

package com.megacity.dao.facory;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.daoImpl.CustomerDAOImpl;

public class CustomerDAOFactory {
    public static CustomerDAO getCustomerDAO() {
        return new CustomerDAOImpl();
    }
}

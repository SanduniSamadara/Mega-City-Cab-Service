package com.megacity.dao.facory;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.DriverDAO;
import com.megacity.dao.daoImpl.DriverDAOImpl;


import java.sql.SQLException;

public class DriverDAOFactory {
    public static DriverDAO getDriverDAO() {
        return new DriverDAOImpl();
    }

}

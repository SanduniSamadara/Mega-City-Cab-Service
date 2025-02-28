package com.megacity.dao.facory;

import com.megacity.dao.DriverDAO;
import com.megacity.dao.daoImpl.DriverDAOImpl;

import java.sql.SQLException;

public class DriverDAOFactory {
    public static DriverDAO getDriverDAO() throws SQLException {
        return new DriverDAOImpl();
    }
}

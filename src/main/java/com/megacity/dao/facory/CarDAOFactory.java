package com.megacity.dao.facory;

import com.megacity.dao.CarDAOImpl;
import com.megacity.dao.daoImpl.CarDAO;

public class CarDAOFactory {
    public static CarDAO getCarDAO() {
        // In this case, we always return an instance of CarDAOImpl
        // But you can expand this to return different implementations based on some criteria
        return new CarDAOImpl();
    }
}

package com.megacity.dao.facory;

import com.megacity.dao.AdminDAO;
import com.megacity.dao.daoImpl.AdminDAOImpl;

public class AdminDAOFactory {

    public static AdminDAO getAdminDAO() {
        return new AdminDAOImpl();
    }
}

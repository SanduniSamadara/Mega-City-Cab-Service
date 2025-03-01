package com.megacity.dao.facory;

import com.megacity.dao.BookingDAO;
import com.megacity.dao.daoImpl.BookingDAOImpl;

public class BookingDAOFactory {
    public static BookingDAO getBookingDAO() {
        return new BookingDAOImpl();
    }
}

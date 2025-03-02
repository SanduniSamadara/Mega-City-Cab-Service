package com.megacity.dao;

import com.megacity.model.Booking;
import java.util.List;

public interface BookingDAO {
    void addBooking(Booking booking) throws Exception;
    Booking getBookingByNumber(String bookingNumber) throws Exception;
    List<Booking> getAllBookings() throws Exception;
    void updateBooking(Booking booking) throws Exception;
    void deleteBooking(String bookingNumber) throws Exception;

    Booking getBookingById(int bookingId);
}

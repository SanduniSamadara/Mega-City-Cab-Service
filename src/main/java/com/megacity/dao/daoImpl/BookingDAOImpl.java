package com.megacity.dao.daoImpl;

import com.megacity.dao.BookingDAO;
import com.megacity.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/megacity";
    private static final String USER = "root";
    private static final String PASSWORD = "yourPassword"; // Change as needed

    @Override
    public void addBooking(Booking booking) throws Exception {
        String sql = "INSERT INTO bookings (booking_number, customer_name, address, telephone, destination) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, booking.getBookingNumber());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getAddress());
            stmt.setString(4, booking.getTelephone());
            stmt.setString(5, booking.getDestination());
            stmt.executeUpdate();
        }
    }

    @Override
    public Booking getBookingByNumber(String bookingNumber) throws Exception {
        Booking booking = null;
        String sql = "SELECT * FROM bookings WHERE booking_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setBookingNumber(rs.getString("booking_number"));
                booking.setCustomerName(rs.getString("customer_name"));
                booking.setAddress(rs.getString("address"));
                booking.setTelephone(rs.getString("telephone"));
                booking.setDestination(rs.getString("destination"));
            }
        }
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() throws Exception {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingNumber(rs.getString("booking_number"));
                booking.setCustomerName(rs.getString("customer_name"));
                booking.setAddress(rs.getString("address"));
                booking.setTelephone(rs.getString("telephone"));
                booking.setDestination(rs.getString("destination"));
                list.add(booking);
            }
        }
        return list;
    }

    @Override
    public void updateBooking(Booking booking) throws Exception {
        String sql = "UPDATE bookings SET customer_name=?, address=?, telephone=?, destination=? WHERE booking_number=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, booking.getCustomerName());
            stmt.setString(2, booking.getAddress());
            stmt.setString(3, booking.getTelephone());
            stmt.setString(4, booking.getDestination());
            stmt.setString(5, booking.getBookingNumber());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteBooking(String bookingNumber) throws Exception {
        String sql = "DELETE FROM bookings WHERE booking_number=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingNumber);
            stmt.executeUpdate();
        }
    }
}

package com.megacity.dao.daoImpl;

import com.megacity.dao.BookingDAO;
import com.megacity.model.Booking;
import com.megacity.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookingDAOImpl implements BookingDAO {

    private static final String ADD_BOOKING_SQL = "INSERT INTO bookings (booking_number, customer_name, address, telephone, destination) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOKING_SQL = "UPDATE bookings SET booking_number = ?, customer_name = ?, address = ?, telephone = ?, destination = ? WHERE booking_number = ?";
    private static final String DELETE_BOOKING_SQL = "DELETE FROM bookings WHERE booking_number = ?";
    private static final String GET_ALL_BOOKINGS_SQL = "SELECT * FROM bookings";
    private static final String GET_BOOKING_BY_ID_SQL = "SELECT * FROM bookings WHERE booking_number = ?";

    @Override
    public void addBooking(Booking booking) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getBookingNumber());
            preparedStatement.setString(2, booking.getCustomerName());
            preparedStatement.setString(3, booking.getAddress());
            preparedStatement.setString(4, booking.getTelephone());
            preparedStatement.setString(5, booking.getDestination());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingByNumber(String bookingNumber) throws Exception {
        return null;
    }

    @Override
    public void updateBooking(Booking booking) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getBookingNumber());
            preparedStatement.setString(2, booking.getCustomerName());
            preparedStatement.setString(3, booking.getAddress());
            preparedStatement.setString(4, booking.getTelephone());
            preparedStatement.setString(5, booking.getDestination());
//            preparedStatement.setInt(6, booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(String bookingNumber) throws Exception {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM bookings WHERE booking_number = ?");
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_BOOKING_SQL)) {

            checkStatement.setString(1, bookingNumber);
            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("No booking found with number: " + bookingNumber);
                return;
            }

            deleteStatement.setString(1, bookingNumber);
            int rowsDeleted = deleteStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Booking deleted successfully.");
            } else {
                System.out.println("Failed to delete booking.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BOOKINGS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
                String orderNumber = resultSet.getString("booking_number");
                String customerName = resultSet.getString("customer_name");
                String address = resultSet.getString("address");
                String telephoneNumber = resultSet.getString("telephone");
                String destinationDetails = resultSet.getString("destination");
                bookings.add(new Booking( orderNumber, customerName, address, telephoneNumber, destinationDetails));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKING_BY_ID_SQL)) {
            preparedStatement.setInt(1, bookingId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
//                    int id = resultSet.getInt("id");
                    String orderNumber = resultSet.getString("booking_number");
                    String customerName = resultSet.getString("customer_name");
                    String address = resultSet.getString("address");
                    String telephoneNumber = resultSet.getString("telephone");
                    String destinationDetails = resultSet.getString("destination");
                    booking = new Booking( orderNumber, customerName, address, telephoneNumber, destinationDetails);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return booking;
    }
}

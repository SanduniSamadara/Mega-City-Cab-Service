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

    private static final String ADD_BOOKING_SQL = "INSERT INTO car_booking (booking_number, customer_name, vehicle_id, driver_id, destination_from, destination_to, distance) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOKING_SQL = "UPDATE car_booking SET booking_number = ?, customer_name = ?, vehicle_id = ?, driver_id = ?, destination_from = ?, destination_to = ?, distance = ?, WHERE booking_number = ?";
    private static final String DELETE_BOOKING_SQL = "DELETE FROM car_booking WHERE booking_number = ?";
    private static final String GET_ALL_BOOKINGS_SQL = "SELECT * FROM car_booking";
    private static final String GET_BOOKING_BY_ID_SQL = "SELECT * FROM car_booking WHERE booking_number = ?";

    @Override
    public void addBooking(Booking booking) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getBookingNumber());
            preparedStatement.setString(2, booking.getCustomerName());
            preparedStatement.setString(3, booking.getVehicle());
            preparedStatement.setString(4, booking.getDriver());
            preparedStatement.setString(5, booking.getDestinationFrom());
            preparedStatement.setString(6, booking.getDestinationTo());
            preparedStatement.setDouble(7, booking.getDistance());

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
            preparedStatement.setString(3, booking.getVehicle());
            preparedStatement.setString(4, booking.getDriver());
            preparedStatement.setString(5, booking.getDestinationFrom());
            preparedStatement.setString(6, booking.getDestinationTo());
            preparedStatement.setDouble(7, booking.getDistance());
//            preparedStatement.setInt(8, booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(String bookingNumber) throws Exception {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM car_booking WHERE booking_number = ?");
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
                String address = resultSet.getString("vehicle_id");
                String telephoneNumber = resultSet.getString("driver_id");
                String destinationFrom = resultSet.getString("destination_from");
                String destinationTo = resultSet.getString("destination_to");
                Double distance = Double.parseDouble(resultSet.getString("distance"));
                bookings.add(new Booking( orderNumber, customerName, address, telephoneNumber, destinationFrom, destinationTo, distance));
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
                    String address = resultSet.getString("vehicle_id");
                    String telephoneNumber = resultSet.getString("driver_id");
                    String destinationFrom = resultSet.getString("destination_from");
                    String destinationTo = resultSet.getString("destination_to");
                    Double distance = Double.parseDouble(resultSet.getString("distance"));
                    booking = new Booking( orderNumber, customerName, address, telephoneNumber, destinationFrom, destinationTo, distance);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return booking;
    }
}

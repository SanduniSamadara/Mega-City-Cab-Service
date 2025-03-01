package test;

import com.megacity.dao.BookingDAO;
import com.megacity.dao.facory.BookingDAOFactory;
import com.megacity.model.Booking;
import java.util.List;

public class BookingTest {
    public static void main(String[] args) {
        // Obtain a BookingDAO instance using the factory
        BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();

        try {
            // 1. Add a new booking
            Booking booking = new Booking("B006", "Samuel Perera", "123 Test Street, Colombo", "0712345678", "Kandy");
            bookingDAO.addBooking(booking);
            System.out.println("Booking added: " + booking);

            // 2. Retrieve the booking by booking number
            Booking retrieved = bookingDAO.getBookingByNumber("B006");
            if (retrieved != null) {
                System.out.println("Retrieved booking: " + retrieved);
            } else {
                System.out.println("Booking not found.");
            }

            // 3. Update the booking details
            if (retrieved != null) {
                retrieved.setCustomerName("Samuel Updated");
                retrieved.setAddress("456 Updated Street, Colombo");
                bookingDAO.updateBooking(retrieved);
                System.out.println("Booking updated: " + retrieved);
            }

            // 4. List all bookings
            List<Booking> allBookings = bookingDAO.getAllBookings();
            System.out.println("All bookings:");
            for (Booking b : allBookings) {
                System.out.println(b);
            }

            // 5. Delete the booking
            bookingDAO.deleteBooking("B006");
            System.out.println("Booking deleted with booking number: B006");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

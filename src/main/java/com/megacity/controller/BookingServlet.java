package com.megacity.controller;

import com.megacity.dao.BookingDAO;
import com.megacity.dao.DriverDAO;
import com.megacity.dao.PaymentDAO;
import com.megacity.dao.facory.BookingDAOFactory;
import com.megacity.dao.facory.DriverDAOFactory;
import com.megacity.dao.facory.PaymentDAOFactory;
import com.megacity.model.Booking;
import com.megacity.model.Driver;
import com.megacity.model.Payment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String orderNumber = request.getParameter("orderNumber");
    String customerName = request.getParameter("customerName");
    String address = request.getParameter("vehicle");
    String telephoneNumber = request.getParameter("driver");
    String destinationFrom = request.getParameter("destinationFrom");
    String destinationTo = request.getParameter("destinationTo");
    double amount = Double.parseDouble(request.getParameter("amount"));
    String paymentMethod = request.getParameter("paymentMethod");
    String status = "Pending";  // You can set status based on your logic
    String currentDate = request.getParameter("date");

    // Handle distance input (ensure it's numeric)
    String distanceStr = request.getParameter("distance");
    distanceStr = distanceStr.replaceAll("[^0-9.]", "");
    double distance = 0;
    try {
        distance = Double.parseDouble(distanceStr);
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }

    // Create a new Booking object
    Booking newBooking = new Booking(orderNumber, customerName, address, telephoneNumber, destinationFrom, destinationTo, distance);

    // Create a new Payment object
    Payment payment = new Payment(orderNumber, amount, paymentMethod, status, currentDate);

    // Perform booking and payment insertion
    BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
    PaymentDAO paymentDAO = PaymentDAOFactory.getPaymentDAO();
    try {
        // First, add the new booking
        bookingDAO.addBooking(newBooking);  // Ensure this executes successfully

        // After the booking is added, proceed with the payment
        paymentDAO.addPayment(payment);  // Ensure this does not violate foreign key constraint
    } catch (Exception e) {
        // Handle exception properly (e.g., log error, rollback transaction, etc.)
        throw new RuntimeException("Error while adding booking and payment", e);
    }

    // Redirect to the booking page after adding the booking and payment
    response.sendRedirect("booking.jsp");
}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String bookingNumber = request.getParameter("bookingNumber");
            if (bookingNumber != null && !bookingNumber.isEmpty()) {
                try {
                    BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
                    Booking booking = bookingDAO.getBookingByNumber(bookingNumber);
                    request.setAttribute("booking", booking);
                    RequestDispatcher rd = request.getRequestDispatcher("editBooking.jsp");
                    rd.forward(request, response);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("booking.jsp"); // Redirect if no valid booking found
        }

        if ("delete".equals(action)) {
            String bookingNumber = request.getParameter("bookingNumber");
            BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
            try {
                bookingDAO.deleteBooking(bookingNumber);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("booking.jsp");
        }
    }
}

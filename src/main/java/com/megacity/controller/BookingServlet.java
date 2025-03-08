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
        // Retrieve parameters from the form
        String orderNumber = request.getParameter("orderNumber");
        String customerName = request.getParameter("customerName");
        String address = request.getParameter("address");
        String telephoneNumber = request.getParameter("telephoneNumber");
        String destinationFrom = request.getParameter("destinationFrom");
        String destinationTo = request.getParameter("destinationTo");
//        double distance = Double.parseDouble(request.getParameter("distance"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String status = "Pending";  // You can set status based on your logic
        String currentDate = request.getParameter("date");


        String distanceStr = request.getParameter("distance");
        distanceStr = distanceStr.replaceAll("[^0-9.]", "");  // Remove anything that is not a number or dot

        // Now parse the cleaned distance
        double distance = 0;
        try {
            distance = Double.parseDouble(distanceStr);
        } catch (NumberFormatException e) {
            // Handle invalid number format if necessary
            // For example, set a default value or show an error message
            e.printStackTrace();
        }

        // Create a new booking object
        Booking newBooking = new Booking(orderNumber, customerName, address, telephoneNumber, destinationFrom, destinationTo, distance);

        // Get the DAO instance and add the booking
        BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
        try {
            bookingDAO.addBooking(newBooking);

            Payment payment = new Payment(orderNumber, amount, paymentMethod, status, currentDate);  // Assuming Payment is a class
            PaymentDAO paymentDAO = PaymentDAOFactory.getPaymentDAO();
            paymentDAO.addPayment(payment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        bookingDAO.addBooking(newBooking); // Add the new booking to the database

        // Redirect to the booking page after adding the booking
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

                    if (booking != null) {
                        request.setAttribute("booking", booking);
                        RequestDispatcher rd = request.getRequestDispatcher("editBooking.jsp");
                        rd.forward(request, response);
                        return; // Ensure no further execution
                    }
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

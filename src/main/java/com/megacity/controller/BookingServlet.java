package com.megacity.controller;

import com.megacity.dao.BookingDAO;
import com.megacity.dao.facory.BookingDAOFactory;
import com.megacity.model.Booking;

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
        String destinationDetails = request.getParameter("destinationDetails");

        // Create a new booking object
        Booking newBooking = new Booking(orderNumber, customerName, address, telephoneNumber, destinationDetails);

        // Get the DAO instance and add the booking
        BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
        try {
            bookingDAO.addBooking(newBooking);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        bookingDAO.addBooking(newBooking); // Add the new booking to the database

        // Redirect to the booking page after adding the booking
        response.sendRedirect("booking.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Handle delete action
        if ("delete".equals(action)) {
            String bookingNumber = request.getParameter("bookingNumber");
            BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
            try {
                bookingDAO.deleteBooking(bookingNumber);// Delete the booking from the database
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("booking.jsp"); // Redirect back to the booking page after deletion
        }
    }
}

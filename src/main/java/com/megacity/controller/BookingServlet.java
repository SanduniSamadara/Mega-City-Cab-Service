package com.megacity.controller;

import com.megacity.dao.BookingDAO;
import com.megacity.dao.daoImpl.BookingDAOImpl;
import com.megacity.model.Booking;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertBooking(request, response);
                    break;
                case "delete":
                    deleteBooking(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateBooking(request, response);
                    break;
                default:
                    listBooking(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // For simplicity, use doGet for all actions.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Booking> listBooking = bookingDAO.getAllBookings();
        request.setAttribute("bookings", listBooking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/bookings.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/booking-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingNumber = request.getParameter("bookingNumber");
        String customerName = request.getParameter("customerName");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String destination = request.getParameter("destination");

        Booking newBooking = new Booking(bookingNumber, customerName, address, telephone, destination);
        bookingDAO.addBooking(newBooking);
        response.sendRedirect("BookingServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingNumber = request.getParameter("bookingNumber");
        Booking existingBooking = bookingDAO.getBookingByNumber(bookingNumber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/booking-form.jsp");
        request.setAttribute("booking", existingBooking);
        dispatcher.forward(request, response);
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingNumber = request.getParameter("bookingNumber");
        String customerName = request.getParameter("customerName");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String destination = request.getParameter("destination");

        Booking booking = new Booking(bookingNumber, customerName, address, telephone, destination);
        bookingDAO.updateBooking(booking);
        response.sendRedirect("BookingServlet");
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingNumber = request.getParameter("bookingNumber");
        bookingDAO.deleteBooking(bookingNumber);
        response.sendRedirect("BookingServlet");
    }
}

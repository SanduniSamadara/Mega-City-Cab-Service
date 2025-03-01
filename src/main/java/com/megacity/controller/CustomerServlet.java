package com.megacity.controller;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.facory.CustomerDAOFactory;
import com.megacity.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrationNumber = request.getParameter("registrationNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");

        Customer newCustomer = new Customer(0, registrationNumber, name, address, nic);

        CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();
        customerDAO.addCustomer(newCustomer); // Add the new customer to the database

        response.sendRedirect("customer.jsp"); // Redirect back to the customer page after adding
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();
            customerDAO.deleteCustomer(customerId); // Delete the customer from the database
            response.sendRedirect("customer.jsp"); // Redirect back to the customer page after deletion
        }
    }
}

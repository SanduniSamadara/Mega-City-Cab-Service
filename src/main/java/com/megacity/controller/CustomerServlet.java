package com.megacity.controller;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.facory.CustomerDAOFactory;
import com.megacity.model.Customer;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrationNumber = request.getParameter("registrationNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String action = request.getParameter("action");
        try {
            CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();

            if ("update".equals(action)) {
                // If it's an update, get the customer ID from the request
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                Customer updatedCustomer = new Customer(customerId, registrationNumber, name, address, nic);
                customerDAO.updateCustomer(updatedCustomer); // Call the DAO method to update
                request.setAttribute("message", "Customer updated successfully"); // Success message
            } else {
                // If it's an add, create a new customer and add to DB
                Customer newCustomer = new Customer(0, registrationNumber, name, address, nic);
                customerDAO.addCustomer(newCustomer);
                request.setAttribute("message", "Customer added successfully");
            }

            // Redirect to the customer page
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing the customer.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
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

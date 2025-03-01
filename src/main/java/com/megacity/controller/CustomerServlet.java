package com.megacity.controller;

import com.megacity.dao.CustomerDAO;
import com.megacity.dao.daoImpl.CarDAO;
import com.megacity.dao.facory.CarDAOFactory;
import com.megacity.dao.facory.CustomerDAOFactory;
import com.megacity.model.Car;
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
        String customerId = request.getParameter("customerId");
        String registrationNumber = request.getParameter("registrationNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");

        CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();

        if (customerId != null && !customerId.isEmpty()) {
            // Updating existing customer
            Customer existingCustomer = new Customer(Integer.parseInt(customerId), registrationNumber, name, address, nic);
            customerDAO.updateCustomer(existingCustomer);
        } else {
            // Adding new customer
            Customer newCustomer = new Customer(0, registrationNumber, name, address, nic);
            customerDAO.addCustomer(newCustomer);
        }

        response.sendRedirect("customer.jsp"); // Redirect to customer list page
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String customerIdParam = request.getParameter("customerId");
            if (customerIdParam != null && !customerIdParam.isEmpty()) {
                try {
                    int customerId = Integer.parseInt(customerIdParam);
                    CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();
                    Customer customer = customerDAO.getCustomerById(customerId);
                    request.setAttribute("customer", customer);
                    RequestDispatcher rd = request.getRequestDispatcher("editCustomer.jsp");
                    rd.forward(request, response);
                    return; // Exit after forwarding
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optionally forward to an error page or set an error attribute
                }
            }
        }


        if ("delete".equals(action)) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();
            customerDAO.deleteCustomer(customerId); // Delete the customer from the database
            response.sendRedirect("customer.jsp"); // Redirect back to the customer page after deletion
        }
    }
}

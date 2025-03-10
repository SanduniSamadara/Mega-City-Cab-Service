package com.megacity.controller;

import com.megacity.dao.daoImpl.AdminDAOImpl;
import com.megacity.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create a new Admin object
        Admin newAdmin = new Admin(0, username, password, role);

        // Instantiate the DAO class and call the addAdmin method
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.addAdmin(newAdmin);

        // Redirect to a confirmation page or send a success message
        response.sendRedirect("admin.jsp?message=Admin Registered Successfully");
    }

    // You could also add doGet method if you plan to handle GET requests.
}

package com.megacity.controller;

import com.megacity.dao.daoImpl.AdminDAOImpl;
import com.megacity.model.Admin;

import javax.servlet.RequestDispatcher;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Admin newAdmin = new Admin(0, username, password, role);

        // Instantiate the DAO class and call the addAdmin method
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.addAdmin(newAdmin);

        request.setAttribute("message", "Admin added successfully");

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);

//        response.sendRedirect("index.jsp");
    }

}

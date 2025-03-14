package com.megacity.controller;

import com.megacity.dao.DriverDAO;
import com.megacity.dao.facory.DriverDAOFactory;
import com.megacity.model.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.sql.DriverManager.registerDriver;

@WebServlet("/DriverServlet")
public class DriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverId = request.getParameter("driverId");
        String licenseNumber = request.getParameter("licenseNumber");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        String errorMessage = null;

        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            errorMessage = "Error: License Number is required.";
        } else if (!licenseNumber.matches("^[A-Za-z0-9]+$")) {  // Only letters and numbers
            errorMessage = "Error: License Number must contain only letters and numbers.";
        }

        DriverDAO driverDAO = DriverDAOFactory.getDriverDAO();

        if (driverId != null && !driverId.isEmpty()) {
            // Updating existing driver
            Driver existingDriver = new Driver(Integer.parseInt(driverId), licenseNumber, name, phone, address);
            driverDAO.updateDriver(existingDriver);
        } else {
            // Adding new driver
            Driver newDriver = new Driver(0, licenseNumber, name, phone, address);
            driverDAO.addDriver(newDriver);
        }

        response.sendRedirect("driver.jsp"); // Redirect to driver list page
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String driverIdParam = request.getParameter("driverId");
            if (driverIdParam != null && !driverIdParam.isEmpty()) {
                try {
                    int driverId = Integer.parseInt(driverIdParam);
                    DriverDAO driverDAO = DriverDAOFactory.getDriverDAO();
                    Driver driver = driverDAO.getDriverById(driverId);
                    request.setAttribute("driver", driver);
                    RequestDispatcher rd = request.getRequestDispatcher("editDriver.jsp");
                    rd.forward(request, response);
                    return; // Exit after forwarding
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optionally forward to an error page or set an error attribute
                }
            }
        }

        if ("delete".equals(action)) {
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            DriverDAO driverDAO = DriverDAOFactory.getDriverDAO();
            driverDAO.deleteDriver(driverId); // Delete the driver from the database
            response.sendRedirect("driver.jsp"); // Redirect back to the driver list page after deletion
        }
    }
//    private void registerDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String licenseNumber = request.getParameter("licenseNumber");
//        String name = request.getParameter("name");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//
//        Driver driver = new Driver(0, licenseNumber, name, phone, address);
//        boolean success = DriverDAO.addDriver(driver);
//
//        if (success) {
//            response.sendRedirect("driverForm.jsp?message=Driver registered successfully");
//        } else {
//            response.sendRedirect("driverForm.jsp?message=Failed to register driver");
//        }
//    }
//
//    private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int driverId = Integer.parseInt(request.getParameter("driverId"));
//        String licenseNumber = request.getParameter("licenseNumber");
//        String name = request.getParameter("name");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//
//        Driver driver = new Driver(driverId, licenseNumber, name, phone, address);
//        boolean success = DriverDAO.updateDriver(driver);
//
//        if (success) {
//            response.sendRedirect("driverForm.jsp?message=Driver updated successfully");
//        } else {
//            response.sendRedirect("driverForm.jsp?message=Failed to update driver");
//        }
//    }
//
//    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int driverId = Integer.parseInt(request.getParameter("driverId"));
//        boolean success = DriverDAO.deleteDriver(driverId);
//
//        if (success) {
//            response.sendRedirect("driverForm.jsp?message=Driver deleted successfully");
//        } else {
//            response.sendRedirect("driverForm.jsp?message=Failed to delete driver");
//        }
//    }
}

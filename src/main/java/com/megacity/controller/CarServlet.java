package com.megacity.controller;

import com.megacity.dao.daoImpl.CarDAO;
import com.megacity.dao.facory.CarDAOFactory;
import com.megacity.model.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carName = request.getParameter("carName");
        String carModel = request.getParameter("carModel");
        String plateNo = request.getParameter("carNo");
        int carYear = Integer.parseInt(request.getParameter("carYear"));
        double carPrice = Double.parseDouble(request.getParameter("carPrice"));

        // Validate input
        if (carName == null || plateNo == null || carYear <= 0 || carPrice <= 0 || carModel == null) {
            throw new IllegalArgumentException("Invalid car details provided");
        }

        Car newCar = new Car(0, carName, plateNo, carYear, carPrice, carModel);

        CarDAO carDAO = CarDAOFactory.getCarDAO();
        carDAO.addCar(newCar); // Add the new car to the database

        response.sendRedirect("car.jsp"); // Redirect back to the car page after adding
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            CarDAO carDAO = CarDAOFactory.getCarDAO();
            carDAO.deleteCar(carId); // Delete the car from the database
            response.sendRedirect("car.jsp"); // Redirect back to the car page after deletion
        }
    }
}

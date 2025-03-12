package com.megacity.controller;

import com.megacity.dao.daoImpl.CarDAO;
import com.megacity.dao.facory.CarDAOFactory;
import com.megacity.model.Car;
import com.megacity.model.Driver;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter("carId");
        String carName = request.getParameter("carName");
        String carModel = request.getParameter("carModel");
        String plateNo = request.getParameter("carNo");
        String carYearParam = request.getParameter("carYear");
        String carPriceParam = request.getParameter("carPrice");

//        if (carName == null || carName.isEmpty() ||
//                plateNo == null || plateNo.isEmpty() ||
//                carModel == null || carModel.isEmpty() ||
//                carYearParam == null || carPriceParam == null) {
//            response.sendRedirect("error.jsp?error=All fields are required.");
//            return; // Redirect with error message
//        }

        int carYear = 0;
        double carPrice = 0.0;

        try {
            carYear = Integer.parseInt(carYearParam);
            carPrice = Double.parseDouble(carPriceParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?error=Invalid input for year or price.");
            return; // Redirect with error message
        }

        // Validate carYear and carPrice
//        if (carYear <= 0 || carPrice <= 0) {
//            response.sendRedirect("error.jsp?error=Invalid car details provided.");
//            return; // Redirect with error message
//        }


        try {
            CarDAO carDAO = CarDAOFactory.getCarDAO();

            if (carId != null && !carId.isEmpty()) {
                // Updating existing car
                Car existingCar = new Car(Integer.parseInt(carId), carName, plateNo, carYear, carPrice, carModel);
                carDAO.updateCar(existingCar); // Update car in DB
                request.setAttribute("message", "Car updated successfully"); // Set success message for forwarding
                RequestDispatcher dispatcher = request.getRequestDispatcher("car.jsp");
                dispatcher.forward(request, response); // Forward to car.jsp with success message
            } else {
                // Adding new car
                Car newCar = new Car(0, carName, plateNo, carYear, carPrice, carModel);
                carDAO.addCar(newCar); // Add car to DB
                request.setAttribute("message", "Car added successfully"); // Set success message for forwarding
                RequestDispatcher dispatcher = request.getRequestDispatcher("car.jsp");
                dispatcher.forward(request, response); // Forward to car.jsp with success message
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=An error occurred while processing the car.");
        }
    }

//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String carIdParam = request.getParameter("carId");
//        String carName = request.getParameter("carName");
//        String carModel = request.getParameter("carModel");
//        String plateNo = request.getParameter("carPlateNumber");
//        String carYearParam = request.getParameter("carYear");
//        String carPriceParam = request.getParameter("carPrice");
//
//        if (carName == null || carName.isEmpty() ||
//                plateNo == null || plateNo.isEmpty() ||
//                carModel == null || carModel.isEmpty() ||
//                carYearParam == null || carPriceParam == null) {
//            response.sendRedirect("car.jsp?error=All fields are required.");
//            return;
//        }
//
//        int carId = Integer.parseInt(carIdParam);
//        int carYear = Integer.parseInt(carYearParam);
//        double carPrice = Double.parseDouble(carPriceParam);
//
//        // Validate carYear and carPrice
//        if (carYear <= 0 || carPrice <= 0) {
//            response.sendRedirect("car.jsp?error=Invalid car details provided.");
//            return;
//        }
//
//        // Create the Car object with updated details
//        Car updatedCar = new Car(carId, carName, plateNo, carYear, carPrice, carModel);
//
//        try {
//            CarDAO carDAO = CarDAOFactory.getCarDAO();
//            carDAO.updateCar(updatedCar); // Update the car in the database
//            response.sendRedirect("car.jsp?message=Car updated successfully.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("car.jsp?error=An error occurred while updating the car.");
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String carIdParam = request.getParameter("carId");
            if (carIdParam != null && !carIdParam.isEmpty()) {
                try {
                    int carId = Integer.parseInt(carIdParam);
                    CarDAO carDAO = CarDAOFactory.getCarDAO();
                    Car car = carDAO.getCarById(carId);
                    request.setAttribute("car", car);
                    RequestDispatcher rd = request.getRequestDispatcher("editCar.jsp");
                    rd.forward(request, response);
                    return; // Exit after forwarding
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optionally forward to an error page or set an error attribute
                }
            }
        }

        if ("delete".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            CarDAO carDAO = CarDAOFactory.getCarDAO();
            carDAO.deleteCar(carId); // Delete the car from the database
            request.setAttribute("message", "Car deleted successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("car.jsp");
            dispatcher.forward(request, response);
//            response.sendRedirect("car.jsp?message=Car deleted successfully.");// Redirect back to the car page after deletion
        }
    }
}

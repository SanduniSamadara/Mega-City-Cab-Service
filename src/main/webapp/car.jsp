<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 2/28/2025
  Time: 5:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.model.Car" %>
<%@ page import="com.megacity.dao.CarDAOImpl" %>
<%@ page import="com.megacity.dao.facory.CarDAOFactory" %>


<%
    // Fetch the list of cars using the DAO factory
    CarDAOImpl carDAO = (CarDAOImpl) CarDAOFactory.getCarDAO();
    List<Car> cars = carDAO.getAllCars();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<!-- Displaying the cars list -->
<div class="container">
    <h2 class="my-4">Car Management</h2>

    <!-- Add Car Form -->
    <div class="mb-4">
        <h4>Add New Car</h4>
        <form action="CarServlet" method="POST">
            <div class="mb-3">
                <label for="carName" class="form-label">Car Name</label>
                <input type="text" class="form-control" id="carName" name="carName" required>
            </div>
            <div class="mb-3">
                <label for="carModel" class="form-label">Car Model</label>
                <input type="text" class="form-control" id="carModel" name="carModel" required>
            </div>
            <div class="mb-3">
                <label for="carModel" class="form-label">Plate Number</label>
                <input type="text" class="form-control" id="carNo" name="carNo" required>
            </div>
            <div class="mb-3">
                <label for="carYear" class="form-label">Year</label>
                <input type="number" class="form-control" id="carYear" name="carYear" required>
            </div>
            <div class="mb-3">
                <label for="carPrice" class="form-label">Price</label>
                <input type="number" class="form-control" id="carPrice" name="carPrice" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Car</button>
        </form>
    </div>

    <!-- Display Cars in a Table -->
    <h4>Existing Cars</h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Car Name</th>
            <th>Car Model</th>
            <th>Year</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Loop through the list of cars and display them in the table
            for (Car car : cars) {
        %>
        <tr>
            <td><%= car.getName() %></td>
            <td><%= car.getModel() %></td>
            <td><%= car.getPlateNumber() %></td>
            <td><%= car.getYear() %></td>
            <td><%= car.getPrice() %></td>
            <td>
                <a href="editCar.jsp?carId=<%= car.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="CarServlet?action=delete&carId=<%= car.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


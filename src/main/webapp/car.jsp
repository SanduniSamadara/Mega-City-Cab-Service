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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.16/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<!-- Displaying the cars list -->
<div class="container">
    <h2 class="my-4">Car Management</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <%
        }
    %>

    <div class="mb-4">
        <h4>Add New Car</h4>
        <form action="CarServlet" method="POST" id="carForm" onsubmit="return validateCarForm()">
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
            <th>Plate Number</th>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.16/dist/sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="application/javascript">
    function validateCarForm() {
        let name = document.getElementById('carName').value;
        let plateNumber = document.getElementById('carNo').value;
        let year = document.getElementById('carYear').value;
        let model = document.getElementById('carModel').value;
        let price = document.getElementById('carPrice').value;

        // Validate car name
        if (name.trim() === '') {
            alert('Car name cannot be empty.');
            return false;
        }
        if (name.length > 100) {
            alert('Car name cannot be more than 100 characters.');
            return false;
        }

        // Validate plate number
        let plateRegex = /^[A-Z0-9]{6,10}$/; // Example for 6-10 characters
        if (!plateRegex.test(plateNumber)) {
            alert('Invalid plate number format.');
            return false;
        }

        // Validate year
        if (year < 1900 || year > new Date().getFullYear()) {
            alert('Invalid year.It must be between 1900 and the current year.');
            return false;
        }

        // Validate model
        if (model.trim() === '') {
            alert('Car model cannot be empty.');
            return false;
        }
        if (model.length > 50) {
            alert('Car model cannot be more than 50 characters.');
            return false;
        }

        // Validate price
        let priceRegex = /^[0-9]+(\.[0-9]{2})?$/; // Validates price with two decimal places
        if (!priceRegex.test(price) || price <= 0) {
            alert('Invalid price. Price must be a positive number with up to two decimal places.');
            return false;
        }

        return true; // If all validations pass, allow form submission
    }

        <%
            // Check if a success message exists
            message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        Swal.fire({
        title: 'Success!',
        text: '<%= message %>',
        icon: 'success',
        showCancelButton: true,
        confirmButtonText: 'OK',
        cancelButtonText: 'Close',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
        // Redirect to index.jsp after clicking OK
        // window.location.href = 'index.jsp';
    }
    });
        <% } %>
</script>
</body>
</html>


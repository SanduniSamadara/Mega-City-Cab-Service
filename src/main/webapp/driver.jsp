<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.megacity.dao.daoImpl.DriverDAOImpl" %>
<%@ page import="com.megacity.dao.facory.DriverDAOFactory" %>
<%@ page import="com.megacity.model.Driver" %>
<%@ page import="com.megacity.dao.DriverDAO" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Fetch the list of drivers using the DAO factory
    DriverDAOImpl driverDAO = (DriverDAOImpl) DriverDAOFactory.getDriverDAO();
    List<Driver> drivers = driverDAO.getAllDrivers();
%>

<% String message = request.getParameter("message");
    if (message != null) { %>
<div class="alert alert-success" role="alert">
    <%= message %>
</div>
<% } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Driver Registration</h1>
    <form action="DriverServlet" method="POST">
        <div class="mb-3">
            <label for="licenseNumber" class="form-label">License Number:</label>
            <input type="text" class="form-control" id="licenseNumber" name="licenseNumber" required>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Phone:</label>
            <input type="text" class="form-control" id="phone" name="phone" required>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>

        <button type="submit" class="btn btn-primary">Register Driver</button>
    </form>

    <h4 class="mt-5">Existing Drivers</h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>License Number</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Iterate through the list of drivers
            for (Driver driver : drivers) {
        %>
        <tr>
            <td><%= driver.getLicenseNumber() %></td>
            <td><%= driver.getName() %></td>
            <td><%= driver.getContactNumber() %></td>
            <td><%= driver.getAddress() %></td>
            <td>
                <a href="editDriver.jsp?driverId=<%= driver.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="DriverServlet?action=delete&driverId=<%= driver.getId() %>" class="btn btn-danger btn-sm">Delete</a>
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

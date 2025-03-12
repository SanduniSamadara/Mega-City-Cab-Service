<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.megacity.model.Car" %>
<%@ page import="com.megacity.dao.CarDAOImpl" %>
<%@ page import="com.megacity.dao.facory.CarDAOFactory" %>

<%
    // Fetch car details for the selected car ID
    int carId = Integer.parseInt(request.getParameter("carId"));
    CarDAOImpl carDAO = (CarDAOImpl) CarDAOFactory.getCarDAO();
    Car car = carDAO.getCarById(carId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Car</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Edit Car Details</h2>
    <form action="CarServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="carId" value="<%= car.getId() %>">

        <div class="mb-3">
            <label for="carName" class="form-label">Car Name</label>
            <input type="text" class="form-control" id="carName" name="carName" value="<%= car.getName() %>" required>
        </div>

        <div class="mb-3">
            <label for="carModel" class="form-label">Car Model</label>
            <input type="text" class="form-control" id="carModel" name="carModel" value="<%= car.getModel() %>" required>
        </div>

        <div class="mb-3">
            <label for="carNo" class="form-label">Plate Number</label>
            <input type="text" class="form-control" id="carNo" name="carNo" value="<%= car.getPlateNumber() %>" required>
        </div>

        <div class="mb-3">
            <label for="carYear" class="form-label">Year</label>
            <input type="number" class="form-control" id="carYear" name="carYear" value="<%= car.getYear() %>" required>
        </div>

        <div class="mb-3">
            <label for="carPrice" class="form-label">Price</label>
            <input type="number" class="form-control" id="carPrice" name="carPrice" value="<%= car.getPrice() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Car</button>
    </form>
</div>
</body>
</html>


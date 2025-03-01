<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.megacity.dao.daoImpl.DriverDAOImpl" %>
<%@ page import="com.megacity.dao.facory.DriverDAOFactory" %>
<%@ page import="com.megacity.model.Driver" %>
<%@ page import="com.megacity.dao.DriverDAO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Fetch driver details for the selected driver ID
    String driverIdParam = request.getParameter("driverId");
    Driver driver = null;

    if (driverIdParam != null && !driverIdParam.isEmpty()) {
        try {
            int driverId = Integer.parseInt(driverIdParam);
            DriverDAOImpl driverDAO = (DriverDAOImpl) DriverDAOFactory.getDriverDAO();
            driver = driverDAO.getDriverById(driverId);
        } catch (NumberFormatException e) {
            out.println("Invalid driver ID.");
        }
    } else {
        out.println("Driver ID is missing.");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Driver</title>
</head>
<body>
<h1>Edit Driver</h1>

<% if (driver != null) { %>
<form action="DriverServlet" method="POST">
    <input type="hidden" name="driverId" value="<%= driver.getId() %>">

    <label for="licenseNumber">License Number:</label><br>
    <input type="text" id="licenseNumber" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required><br><br>

    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="<%= driver.getName() %>" required><br><br>

    <label for="phone">Phone:</label><br>
    <input type="text" id="phone" name="phone" value="<%= driver.getPhone() %>" required><br><br>

    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" value="<%= driver.getAddress() %>" required><br><br>

    <input type="submit" value="Update Driver">
</form>
<% } else { %>
<p>Driver details could not be loaded.</p>
<% } %>

</body>
</html>

<%@ page import="com.megacity.dao.daoImpl.CustomerDAOImpl" %>
<%@ page import="com.megacity.dao.facory.CustomerDAOFactory" %>
<%@ page import="com.megacity.model.Customer" %>
<%@ page import="com.megacity.dao.CustomerDAO" %><%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    // Fetch car details for the selected car ID
    int customerId = Integer.parseInt(request.getParameter("customerId"));
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) CustomerDAOFactory.getCustomerDAO();
    Customer customer = customerDAO.getCustomerById(customerId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>

</head>
<body>
<h1>Edit Customer</h1>
<form action="CustomerServlet" method="POST">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="customerId" value="<%= customer.getId() %>">

    <label for="registrationNumber">Registration Number:</label><br>
    <input type="text" id="registrationNumber" name="registrationNumber" value="<%= customer.getRegistrationNumber() %>" required><br><br>

    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="<%= customer.getName() %>" required><br><br>

    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required><br><br>

    <label for="nic">NIC:</label><br>
    <input type="text" id="nic" name="nic" value="<%= customer.getNic() %>" required><br><br>

    <input type="submit" value="Update Customer">
</form>
</body>
</html>

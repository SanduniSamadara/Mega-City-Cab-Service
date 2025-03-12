<%@ page import="com.megacity.dao.daoImpl.CustomerDAOImpl" %>
<%@ page import="com.megacity.dao.facory.CustomerDAOFactory" %>
<%@ page import="com.megacity.model.Customer" %>
<%@ page import="com.megacity.dao.CustomerDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 2/28/2025
  Time: 6:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Fetch the list of cars using the DAO factory
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) CustomerDAOFactory.getCustomerDAO();
    List<Customer> customers = customerDAO.getAllCustomers();
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
    <title>Customer Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

</head>
<body>
<h1>Customer Registration</h1>
<form action="CustomerServlet" method="POST" onsubmit="return validateForm()">
    <label for="registrationNumber">Registration Number:</label><br>
    <input type="text" id="registrationNumber" name="registrationNumber" required><br><br>

    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" required><br><br>

    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" required><br><br>

    <label for="nic">NIC:</label><br>
    <input type="text" id="nic" name="nic" required><br><br>

    <input type="submit" value="Register Customer">
</form>

<h4>Existing Customers</h4>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Registration Number</th>
        <th>Name</th>
        <th>Address</th>
        <th>NIC</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assuming 'customers' is a list of Customer objects fetched from the database
        for (Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getRegistrationNumber() %></td>
        <td><%= customer.getName() %></td>
        <td><%= customer.getAddress() %></td>
        <td><%= customer.getNic() %></td>
        <td>
            <a href="editCustomer.jsp?customerId=<%= customer.getId() %>" class="btn btn-warning btn-sm">Edit</a>
            <a href="CustomerServlet?action=delete&customerId=<%= customer.getId() %>" class="btn btn-danger btn-sm">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function validateForm() {
        const regNumber = document.getElementById("registrationNumber").value;
        const name = document.getElementById("name").value;
        const address = document.getElementById("address").value;
        const nic = document.getElementById("nic").value;

        // Check if any field is empty
        if (!regNumber || !name || !address || !nic) {
            alert("All fields are required.");
            return false;
        }

        // Validate NIC format
        const nicPattern = /^(?:\d{9}[Vv]?|\d{12})$/;
        if (!nic.match(nicPattern)) {
            alert("Please enter a valid NIC");
            return false;
        }
        const namePattern = /^[a-zA-Z\s]+$/; // Only allows letters and spaces
        if (!name.match(namePattern)) {
            alert("Name cannot contain special characters ");
            return false;
        }

        return true; // Form is valid
    }
</script>
</body>
</html>

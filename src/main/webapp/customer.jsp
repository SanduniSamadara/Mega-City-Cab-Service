<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 2/28/2025
  Time: 6:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
</head>
<body>
<h1>Customer Registration</h1>
<form action="CustomerServlet" method="POST">
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
</body>
</html>

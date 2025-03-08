<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/2/2025
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>



<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacity.model.Booking" %>

<%
  Booking booking = (Booking) request.getAttribute("booking");
  if (booking == null) {
    response.sendRedirect("booking.jsp"); // Redirect if no booking data is found
    return;
  }
%>

<html>
<head>
  <title>Edit Booking</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    .form-container {
      width: 50%;
      margin: 20px auto;
      padding: 20px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .form-container h1 {
      text-align: center;
    }
    .form-container label {
      display: block;
      margin: 10px 0 5px;
    }
    .form-container input[type="text"],
    .form-container input[type="submit"] {
      width: 100%;
      padding: 8px;
      margin: 5px 0 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .form-container input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
    .form-container input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>

<div class="form-container">
  <h1>Edit Booking</h1>
  <form action="BookingServlet" method="post">
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="bookingNumber" value="<%= booking.getBookingNumber() %>" />

    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="customerName" value="<%= booking.getCustomerName() %>" required />

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="<%= booking.getAddress() %>" required />

    <label for="telephoneNumber">Telephone Number:</label>
    <input type="text" id="telephoneNumber" name="telephoneNumber" value="<%= booking.getTelephone() %>" required />

    <label for="destinationDetails">Destination Details:</label>
    <input type="text" id="destinationDetails" name="destinationDetails" value="<%= booking.getDestination() %>" required />

    <input type="submit" value="Update Booking" />
  </form>
</div>

</body>
</html>

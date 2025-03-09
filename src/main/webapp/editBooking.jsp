<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/2/2025
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacity.model.Booking" %>
<%@ page import="com.megacity.dao.BookingDAO" %>
<%@ page import="com.megacity.dao.daoImpl.BookingDAOImpl" %>

<%
  // Get the bookingNumber from the URL parameter
  String bookingNumber = request.getParameter("bookingNumber");

  if (bookingNumber == null || bookingNumber.isEmpty()) {
    // If no booking number is found, redirect to the bookings list page
    response.sendRedirect("booking.jsp");
    return;
  }

  // Instantiate BookingDAOImpl to interact with the database
  BookingDAOImpl bookingDAO = new BookingDAOImpl();
  Booking booking = null;

  try {
    // Fetch the booking details by bookingNumber
    booking = bookingDAO.getBookingByNumber(bookingNumber);
  } catch (Exception e) {
    e.printStackTrace();
    // Handle the exception accordingly, maybe redirect or show an error page
  }

  if (booking == null) {
    // If booking not found, redirect to booking list
    response.sendRedirect("booking.jsp");
    return;
  }
%>

<html>
<head>
  <title>Edit Booking</title>
  <style>
    /* Add your CSS styling */
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
    <!-- Action to indicate that this is an update -->
    <input type="hidden" name="action" value="update" />
    <!-- Hidden field to carry the booking number -->
    <input type="hidden" name="bookingNumber" value="<%= booking.getBookingNumber() %>" />

    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="customerName" value="<%= booking.getCustomerName() %>" required />

    <label for="driver">Driver:</label>
    <input type="text" id="driver" name="driver" value="<%= booking.getDriver() %>" required />

    <label for="vehicle">Telephone Number:</label>
    <input type="text" id="vehicle" name="vehicle" value="<%= booking.getVehicle() %>" required />

    <label for="destinationFrom">Destination Details:</label>
    <input type="text" id="destinationFrom" name="destinationFrom" value="<%= booking.getDestinationFrom() %>" required />

    <label for="destinationTo">Destination Details:</label>
    <input type="text" id="destinationTo" name="destinationTo" value="<%= booking.getDestinationTo() %>" required />

    <label for="distance">Distance:</label>
    <input type="text" id="distance" name="distance" value="<%= booking.getDistance() %>" required />

    <input type="submit" value="Update Booking" />
  </form>
</div>

</body>
</html>

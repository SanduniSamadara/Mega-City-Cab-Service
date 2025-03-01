<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacity.model.Booking" %>
<html>
<head>
    <title><%= request.getAttribute("booking") == null ? "Add New Booking" : "Edit Booking" %></title>
</head>
<body>
<h1><%= request.getAttribute("booking") == null ? "Add New Booking" : "Edit Booking" %></h1>
<form action="BookingServlet" method="get">
    <input type="hidden" name="action" value="<%= request.getAttribute("booking") == null ? "insert" : "update" %>" />

    <label for="bookingNumber">Booking Number:</label>
    <input type="text" name="bookingNumber" id="bookingNumber" value="<%= request.getAttribute("booking") != null ? ((Booking)request.getAttribute("booking")).getBookingNumber() : "" %>" required/><br/><br/>

    <label for="customerName">Customer Name:</label>
    <input type="text" name="customerName" id="customerName" value="<%= request.getAttribute("booking") != null ? ((Booking)request.getAttribute("booking")).getCustomerName() : "" %>" required/><br/><br/>

    <label for="address">Address:</label>
    <input type="text" name="address" id="address" value="<%= request.getAttribute("booking") != null ? ((Booking)request.getAttribute("booking")).getAddress() : "" %>" required/><br/><br/>

    <label for="telephone">Telephone:</label>
    <input type="text" name="telephone" id="telephone" value="<%= request.getAttribute("booking") != null ? ((Booking)request.getAttribute("booking")).getTelephone() : "" %>" required/><br/><br/>

    <label for="destination">Destination:</label>
    <input type="text" name="destination" id="destination" value="<%= request.getAttribute("booking") != null ? ((Booking)request.getAttribute("booking")).getDestination() : "" %>" required/><br/><br/>

    <input type="submit" value="Submit" />
</form>
<br/>
<a href="BookingServlet">Back to Booking List</a>
</body>
</html>

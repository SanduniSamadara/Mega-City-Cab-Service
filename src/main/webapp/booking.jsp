<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.model.Booking" %>
<html>
<head>
    <title>Mega City Cab - Booking List</title>
</head>
<body>
<h1>Booking List</h1>
<table border="1">
    <tr>
        <th>Booking Number</th>
        <th>Customer Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Destination</th>
        <th>Actions</th>
    </tr>
    <%
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        if (bookings != null && !bookings.isEmpty()) {
            for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getBookingNumber() %></td>
        <td><%= booking.getCustomerName() %></td>
        <td><%= booking.getAddress() %></td>
        <td><%= booking.getTelephone() %></td>
        <td><%= booking.getDestination() %></td>
        <td>
            <a href="BookingServlet?action=edit&bookingNumber=<%= booking.getBookingNumber() %>">Edit</a> |
            <a href="BookingServlet?action=delete&bookingNumber=<%= booking.getBookingNumber() %>" onclick="return confirm('Are you sure?');">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No bookings available.</td>
    </tr>
    <%
        }
    %>
</table>
<br/>
<a href="BookingServlet?action=new">Add New Booking</a>
</body>
</html>

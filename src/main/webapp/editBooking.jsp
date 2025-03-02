<%@ page import="com.megacity.dao.BookingDAO" %>
<%@ page import="com.megacity.dao.facory.BookingDAOFactory" %>
<%@ page import="com.megacity.model.Booking" %><%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/2/2025
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>



<%
  String bookingNumber = request.getParameter("bookingNumber");
  BookingDAO bookingDAO = BookingDAOFactory.getBookingDAO();
  Booking booking = null;

  try {
    booking = bookingDAO.getBookingByNumber(bookingNumber);
  } catch (Exception e) {
    e.printStackTrace();
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Edit Booking</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<h2>Edit Booking</h2>

<% if (booking != null) { %>
<form action="BookingServlet?action=update" method="post">
  <input type="hidden" name="bookingNumber" value="<%= booking.getBookingNumber() %>">

  <label for="bookingNo">Booking No:</label>
  <input type="text" id="bookingNo" name="bookingNo" value="<%= booking.getBookingNumber() %>" required>

  <label for="customerName">Customer Name:</label>
  <input type="text" id="customerName" name="customerName" value="<%= booking.getCustomerName() %>" required>

  <label for="address">Date:</label>
  <input type="text" id="address" name="address" value="<%= booking.getAddress() %>" required>

  <label for="telephone">Time:</label>
  <input type="number" id="telephone" name="telephone" value="<%= booking.getTelephone() %>" required>

  <label for="destination">Service:</label>
  <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required>

  <input type="submit" value="Update Booking">
</form>
<% } else { %>
<p>Booking not found.</p>
<% } %>
</body>
</html>

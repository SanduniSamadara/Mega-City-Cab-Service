<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/1/2025
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacity.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.dao.daoImpl.BookingDAOImpl" %>
<%@ page import="com.megacity.dao.facory.BookingDAOFactory" %>

<link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>

<%
    // Fetch the list of cars using the DAO factory
    BookingDAOImpl bookingDAO = (BookingDAOImpl) BookingDAOFactory.getBookingDAO();
    List<Booking> bookings = bookingDAO.getAllBookings();
%>

<% String message = request.getParameter("message");
    if (message != null) { %>
<div class="alert alert-success" role="alert">
    <%= message %>
</div>
<% } %>


<html>
<head>
    <title>Booking Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .form-container {
            width: 60%;
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
        .form-container input[type="number"],
        .form-container input[type="submit"],
        .form-container select {
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .action-buttons a {
            padding: 5px 10px;
            margin: 2px;
            background-color: #f1f1f1;
            border-radius: 4px;
            color: #333;
            text-decoration: none;
        }
        .action-buttons a:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>

<%-- Add New Booking Form --%>
<div class="form-container">
    <h1>Add New Booking</h1>

    <form action="payment.jsp" method="post">
        <input type="hidden" name="action" value="insert" />

        <label for="orderNumber">Order Number:</label>
        <input type="text" id="orderNumber" name="orderNumber" required />

        <label for="customerName">Customer Name:</label>
        <input type="text" id="customerName" name="customerName" required />

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required />

        <label for="telephoneNumber">Telephone Number:</label>
        <input type="text" id="telephoneNumber" name="telephoneNumber" required />

        <label for="destinationFrom">Destination From:</label>
<%--        <input type="text" id="destinationFrom" name="destinationFrom" required />--%>
        <select id="destinationFrom" name="destinationFrom"></select>

        <label for="destinationTo">Destination To:</label>
<%--        <input type="text" id="destinationTo" name="destinationTo" required />--%>
        <select id="destinationTo" name="destinationTo"></select>

        <label for="distance">Distance(km):</label>
        <input type="text" id="distance" name="distance" required />
<%--        <input type="submit" value="Add Booking" />--%>
        <input type="submit" value="Next" />
    </form>
</div>

<%-- List of Existing Bookings --%>
<div class="form-container">
    <h1>Booking List</h1>

    <table>
        <thead>
        <tr>
            <th>Order Number</th>
            <th>Customer Name</th>
            <th>Address</th>
            <th>Telephone Number</th>
            <th>Destination Details</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Loop through the list of cars and display them in the table
            for (Booking booking : bookings) {
        %>
        <tr>
            <td><%= booking.getBookingNumber() %></td>
            <td><%= booking.getCustomerName() %></td>
            <td><%= booking.getAddress() %></td>
            <td><%= booking.getTelephone() %></td>
            <td><%= booking.getDestination() %></td>
            <td class="action-buttons">
                <a href="BookingServlet?action=edit&bookingNumber=<%= booking.getBookingNumber() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="BookingServlet?action=delete&bookingNumber=<%= booking.getBookingNumber() %>" onclick="return confirm('Are you sure?');" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>

<%--        <tr>--%>
<%--&lt;%&ndash;            <td colspan="6">No bookings available.</td>&ndash;%&gt;--%>
<%--        </tr>--%>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function addBooking() {
        // Get form values
        let orderNumber = document.getElementById("orderNumber").value;
        let customerName = document.getElementById("customerName").value;
        let address = document.getElementById("address").value;
        let telephoneNumber = document.getElementById("telephoneNumber").value;
        let destinationDetails = document.getElementById("destinationDetails").value;

        if (!orderNumber || !customerName || !address || !telephoneNumber || !destinationDetails) {
            alert("Please fill all fields.");
            return;
        }

        // Create a new row
        let table = document.getElementById("bookingTable").getElementsByTagName('tbody')[0];
        let newRow = table.insertRow();

        newRow.innerHTML = `
            <td>${orderNumber}</td>
            <td>${customerName}</td>
            <td>${address}</td>
            <td>${telephoneNumber}</td>
            <td>${destinationDetails}</td>
            <td class="action-buttons">
                <button onclick="editRow(this)">Edit</button>
                <button onclick="deleteRow(this)">Delete</button>
            </td>
        `;

        // Clear form fields
        document.getElementById("bookingForm").reset();
    }

    function editRow(button) {
        let row = button.closest("tr");
        let cells = row.getElementsByTagName("td");

        document.getElementById("orderNumber").value = cells[0].innerText;
        document.getElementById("customerName").value = cells[1].innerText;
        document.getElementById("address").value = cells[2].innerText;
        document.getElementById("telephoneNumber").value = cells[3].innerText;
        document.getElementById("destinationDetails").value = cells[4].innerText;

        // Remove the row from the table
        row.remove();
    }

    function deleteRow(button) {
        let row = button.closest("tr");
        row.remove();
    }

    $(document).ready(function() {
        // Function to populate dropdown with Sri Lankan districts
        function loadDistricts() {
            $.getJSON('districts.json', function(data) {
                // Loop through the districts and append them to the dropdown
                var options = '';
                data.forEach(function(district) {
                    options += '<option value="' + district + '">' + district + '</option>';
                });
                $('#destinationFrom, #destinationTo').html(options);
            });
        }

        // Apply Select2 to the dropdowns
        $('#destinationFrom, #destinationTo').select2({
            placeholder: "Select a district",
            allowClear: true
        });

        // Load the districts dynamically
        loadDistricts();
    });

</script>
</body>
</html>

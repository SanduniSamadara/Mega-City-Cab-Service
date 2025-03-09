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
<%@ page import="java.sql.*, java.util.*" %>

<link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAW684kX04SvFpSFyuFKIEnSn17lbqegLM&libraries=places" async defer></script>--%>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCuE7Y6ZroRrz93R-PPmG2n4H_btKOU398&libraries=places" async defer></script>

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

<%
    // Define database connection parameters
    String url = "jdbc:mysql://localhost:3306/megacitycab";
    String user = "root";
    String password = "1234";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // Initialize lists to hold data
    List<String> customers = new ArrayList<>();
    List<String> drivers = new ArrayList<>();
    List<String> vehicles = new ArrayList<>();

    try {
        // Establish connection
        connection = DriverManager.getConnection(url, user, password);

        // Fetch customers
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT registration_number, name FROM customers");

        while (resultSet.next()) {
            String customerId = resultSet.getString("registration_number");
            String customerName = resultSet.getString("name");
            customers.add(customerId + ":" + customerName);

        }


        // Fetch drivers
        resultSet = statement.executeQuery("SELECT id, name FROM drivers");

        while (resultSet.next()) {
            String driverId = resultSet.getString("id");
            String driverName = resultSet.getString("name");
            drivers.add(driverId + ":" + driverName);
        }

        // Fetch vehicles
        resultSet = statement.executeQuery("SELECT id, name FROM cars");
        while (resultSet.next()) {
            vehicles.add(resultSet.getString("id") + ":" + resultSet.getString("name"));
        }
        while (resultSet.next()) {
            String vehicleId = resultSet.getString("id");
            String vehicleName = resultSet.getString("name");
            vehicles.add(vehicleId + ":" + vehicleName);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<html>
<head>
    <title>Booking Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />

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
        /*.action-buttons a {*/
        /*    padding: 5px 10px;*/
        /*    margin: 2px;*/
        /*    background-color: #f1f1f1;*/
        /*    border-radius: 4px;*/
        /*    color: #333;*/
        /*    text-decoration: none;*/
        /*}*/
        /*.action-buttons a:hover {*/
        /*    background-color: #ddd;*/
        /*}*/
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
        <select id="customerName" name="customerName" required>
            <option value="" selected disabled>Select Customer</option>
            <%
                for (String customer : customers) {
                    String[] customerData = customer.split(":");
                    String customerId = customerData[0];
                    String customerName = customerData[1];
            %>
            <option value="<%= customerId %>"><%= customerId %> - <%= customerName %></option>
            <% } %>
        </select>

        <label for="driver">Driver:</label>
        <select id="driver" name="driver" >
            <option value="" selected disabled>Select Driver</option>
            <%
                for (String driver : drivers) {
                    String[] driverData = driver.split(":");
                    String driverId = driverData[0];
                    String driverName = driverData[1];
            %>
            <option value="<%= driverId %>"><%= driverId %> - <%= driverName %></option>
            <% } %>
        </select>

        <label for="vehicle">Vehicle:</label>
        <select id="vehicle" name="vehicle" >
            <option value="" selected disabled>Select Vehicle</option>
            <%
                for (String vehicle : vehicles) {
                    String[] vehicleData = vehicle.split(":");
                    String vehicleId = vehicleData[0];
                    String vehicleName = vehicleData[1];
            %>
            <option value="<%= vehicleId %>"><%= vehicleName %></option>
            <% } %>
        </select>

        <label for="destinationFrom">Destination From:</label>
<%--        <input type="text" id="destinationFrom" name="destinationFrom" required />--%>
        <select id="destinationFrom" name="destinationFrom"></select>

        <label for="destinationTo">Destination To:</label>
<%--        <input type="text" id="destinationTo" name="destinationTo" required />--%>
        <select id="destinationTo" name="destinationTo"></select>

        <label for="distance">Distance(km):</label>
        <input type="text" id="distance" name="distance"  />
<%--        <input type="submit" value="Add Booking" />--%>
        <input type="submit" value="Next" onclick="location.href='payment.jsp'"/>
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
            <th>Vehicle</th>
            <th>Driver Name</th>
            <th>Destination From</th>
            <th>Destination To</th>
            <th>Distance</th>
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
            <td><%= booking.getVehicle() %></td>
            <td><%= booking.getDriver() %></td>
            <td><%= booking.getDestinationFrom() %></td>
            <td><%= booking.getDestinationTo() %></td>
            <td><%= booking.getDistance() %></td>
            <td>
<%--                <a href="BookingServlet?action=edit&bookingNumber=<%= booking.getBookingNumber() %>" class="btn btn-warning btn-sm edit">Edit</a>--%>
    <a href="editBooking.jsp?bookingNumber=<%= booking.getBookingNumber() %>" class="btn btn-warning btn-sm">Edit</a>
    <a href="BookingServlet?action=delete&bookingNumber=<%= booking.getBookingNumber() %>" onclick="return confirm('Are you sure?');" class="btn btn-danger btn-sm delete">Delete</a>
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
        let address = document.getElementById("driver").value;
        let telephoneNumber = document.getElementById("vehicle").value;
        let destinationFrom = document.getElementById("destinationFrom").value;
        let destinationTo = document.getElementById("destinationTo").value;
        let distance = document.getElementById("distance").value;

        if (!orderNumber || !customerName || !address || !telephoneNumber || !destinationFrom || !destinationTo || !distance) {
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
            <td>${destinationFrom}</td>
             <td>${destinationTo}</td>
              <td>${distance}</td>
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
        document.getElementById("destinationFrom").value = cells[4].innerText;
        document.getElementById("destinationTo").value = cells[5].innerText;
        document.getElementById("distance").value = cells[6].innerText;
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
            $.getJSON('towns.json', function(data) {
                // Loop through the districts and append them to the dropdown
                var options = '<option value="" selected disabled>Select a Location</option>';
                data.forEach(function(district) {
                    options += '<option value="' + district + '">' + district + '</option>';
                });
                $('#destinationFrom, #destinationTo').html(options);
            });
        }

        // Apply Select2 to the dropdowns
        $('#destinationFrom, #destinationTo').select2({
            placeholder: "Select a Location",
            allowClear: true
        });

        // Load the districts dynamically
        loadDistricts();

        $('#destinationFrom, #destinationTo').change(function() {
            var from = $('#destinationFrom').val();
            var to = $('#destinationTo').val();

            if (from && to) {
                calculateDistance(from, to);
            }
        });

        function calculateDistance(from, to) {
            var service = new google.maps.DistanceMatrixService();
            service.getDistanceMatrix(
                {
                    origins: [from],
                    destinations: [to],
                    travelMode: google.maps.TravelMode.DRIVING,
                },
                function(response, status) {
                    if (status == google.maps.DistanceMatrixStatus.OK) {
                        var originList = response.originAddresses;
                        var destinationList = response.destinationAddresses;
                        var distance = response.rows[0].elements[0].distance.text;
                        $('#distance').val(distance);
                        console.log("Distance: " + distance);
                    } else {
                        alert("Error with Distance Matrix request: " + status);
                    }
                }
            );
        }
    });

</script>
</body>
</html>

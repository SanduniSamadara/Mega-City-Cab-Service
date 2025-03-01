<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession sessionUser = request.getSession(false);
    if (sessionUser == null || sessionUser.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String username = (String) sessionUser.getAttribute("username");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .dashboard-container {
            max-width: 900px;
            margin: 50px auto;
        }
        .card {
            cursor: pointer;
            transition: 0.3s;
        }
        .card:hover {
            background-color: #f8f9fa;
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="container text-center dashboard-container">
    <h2 class="mb-4">Welcome, <%= username %>!</h2>

    <div class="row">
        <!-- Car Management -->
        <div class="col-md-4">
            <div class="card p-3" onclick="location.href='car.jsp'">
                <h5>🚗 Car Management</h5>
                <p>Manage car details.</p>
            </div>
        </div>

        <!-- Vehicle Management -->
        <div class="col-md-4">
            <div class="card p-3" onclick="location.href='customer.jsp'">
                <h5>🧑‍💼 Customer Management</h5>
                <p>View and update customer records.</p>
            </div>
        </div>

        <!-- Driver Management -->
        <div class="col-md-4">
            <div class="card p-3" onclick="location.href='driverManagement.jsp'">
                <h5>👨‍✈️ Driver Management</h5>
                <p>Manage driver information.</p>
            </div>
        </div>

        <!-- Booking Management -->
        <div class="col-md-4 mt-3">
            <div class="card p-3" onclick="location.href='bookingManagement.jsp'">
                <h5>📅 Booking Management</h5>
                <p>Handle customer bookings.</p>
            </div>
        </div>

        <!-- Bill Payments -->
        <div class="col-md-4 mt-3">
            <div class="card p-3" onclick="location.href='billPayments.jsp'">
                <h5>💳 Bill Payments</h5>
                <p>Manage and process payments.</p>
            </div>
        </div>

        <!-- Logout -->
        <div class="col-md-4 mt-3">
            <div class="card p-3 bg-danger text-white" onclick="location.href='logout'">
                <h5>🚪 Logout</h5>
                <p>Sign out from your account.</p>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

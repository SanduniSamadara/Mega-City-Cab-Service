<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/8/2025
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String orderNumber = request.getParameter("orderNumber");
    String customerName = request.getParameter("customerName");
    String driver = request.getParameter("driver");
    String vehicle = request.getParameter("vehicle");
    String destinationFrom = request.getParameter("destinationFrom");
    String destinationTo = request.getParameter("destinationTo");
    String distance = request.getParameter("distance");

    String status = "Pending";

    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String currentDate = formatter.format(new java.util.Date());
%>
<html>
<head>
    <title>Payment Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; padding: 20px; border: 1px solid #ddd; border-radius: 10px; }
        label { font-weight: bold; margin-top: 10px; display: block; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; border-radius: 5px; border: 1px solid #ccc; }
        input[type="submit"] { background-color: #28a745; color: white; font-weight: bold; cursor: pointer; margin-top: 15px; }
        input[type="submit"]:hover { background-color: #218838; }
        .readonly { background-color: #f4f4f4; cursor: not-allowed; }
    </style>
</head>
<body>

<h2>Payment Details</h2>
<form action="BookingServlet" method="post" onsubmit="return showSuccessAlert();">
    <input type="hidden" name="action" value="process_payment" />

    <!-- Booking Details -->
    <input type="hidden" name="orderNumber" value="<%= orderNumber %>" />
    <input type="hidden" name="customerName" value="<%= customerName %>" />
    <input type="hidden" name="driver" value="<%= driver %>" />
    <input type="hidden" name="vehicle" value="<%= vehicle %>" />
    <input type="hidden" name="destinationFrom" value="<%= destinationFrom %>" />
    <input type="hidden" name="destinationTo" value="<%= destinationTo %>" />
    <input type="hidden" name="distance" value="<%= distance != null ? distance : "" %>"  oninput="calculateAmount()"/>

    <!-- Payment Fields -->
    <label for="amount">Amount (LKR):</label>
    <input type="number" id="amount" name="amount" required />

    <input type="hidden" id="hiddenDistance" name="hiddenDistance" />

    <label for="paymentMethod">Payment Method:</label>
    <select id="paymentMethod" name="paymentMethod">
        <option value="credit_card">Credit Card</option>
        <option value="cash">Cash</option>
        <option value="bank_transfer">Bank Transfer</option>
        <option value="mobile_wallet">Mobile Wallet</option>
    </select>

    <label for="status">Payment Status:</label>
    <input type="text" id="status" name="status" value="Pending" class="readonly" readonly />

    <label for="date">Payment Date:</label>
    <input type="text" id="date" name="date" value="<%= currentDate %>" class="readonly" readonly />

    <input type="submit" value="Complete Booking & Payment" />
</form>
<script type="text/javascript">
    function updatePaymentStatus(status) {
        // This function updates the payment status message based on payment processing status
        var statusElement = document.getElementById("paymentStatus");
        if (status === "Pending") {
            statusElement.innerHTML = "Payment is pending.";
            statusElement.style.color = "orange";
        } else if (status === "Completed") {
            statusElement.innerHTML = "Payment Successful!";
            statusElement.style.color = "green";
        } else {
            statusElement.innerHTML = "Payment Failed. Please try again.";
            statusElement.style.color = "red";
        }
    }

    function processPaymentAndDisplayStatus() {
        // Simulate payment processing logic (you can replace this with actual API calls)
        var paymentMethod = document.getElementById("paymentMethod").value;
        var amount = document.getElementById("amount").value;

        // Simple validation
        if (amount === "" || parseFloat(amount) <= 0) {
            alert("Please enter a valid payment amount.");
            return;
        }

        // Simulate random payment success or failure
        var paymentSuccess = Math.random() > 0.5; // Random success/failure for demo purposes

        if (paymentSuccess) {
            updatePaymentStatus("Completed");
        } else {
            updatePaymentStatus("Failed");
        }

        // Prevent form submission to simulate payment completion process
        event.preventDefault();  // Uncomment to prevent actual form submission
    }

    function calculateAmount() {
        const ratePerKm = 50; // Rate: 1 km = 50

        const distanceInput = document.querySelector('input[name="distance"]');
        let distance = distanceInput.value;

        // Remove any non-numeric characters (e.g., "km")
        distance = distance.replace(/[^\d.-]/g, '');

        console.log("Cleaned distance:", distance);

        if (distance && !isNaN(distance) && parseFloat(distance) > 0) {
            const amount = parseFloat(distance) * ratePerKm;
            document.getElementById("amount").value = amount;
            document.getElementById("hiddenDistance").value = distance;
        } else {
            console.log("Invalid distance entered.");
            document.getElementById("amount").value = '';
            document.getElementById("hiddenDistance").value = '';

        }
    }

    window.onload = function() {
        calculateAmount();
    }

    function showSuccessAlert() {
        // Display the success message
        alert("Booking and Payment Completed Successfully! ");
        return true;
    }
</script>
</body>
</html>
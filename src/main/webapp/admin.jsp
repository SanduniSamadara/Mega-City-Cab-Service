<%--
  Created by IntelliJ IDEA.
  User: Samadhara
  Date: 3/11/2025
  Time: 1:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacity.model.Admin" %>
<%@ page import="com.megacity.dao.daoImpl.AdminDAOImpl" %>
<%@ page import="com.megacity.dao.facory.AdminDAOFactory" %>
<%@ page import="java.util.List" %>

<%--<%--%>
<%--  // Fetch the admin list (if necessary)--%>
<%--  AdminDAOImpl adminDAO = (AdminDAOImpl) AdminDAOFactory.getAdminDAO();--%>
<%--  List<Admin> admins = adminDAO.getAllAdmins();--%>
<%--%>--%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Registration</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.16/dist/sweetalert2.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
 <center> <h2 class="my-4">Admin Registration</h2></center>

  <%
    String message = (String) request.getAttribute("message");
    if (message != null) {
  %>
  <%
    }
  %>

  <div class="mb-4">
    <form action="AdminServlet" method="POST">
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text" class="form-control" id="username" name="username" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>
      <div class="mb-3">
        <label for="role" class="form-label">Role</label>
        <select class="form-select" id="role" name="role" required>
          <option value="admin">Admin</option>
          <option value="user">User</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Register Admin</button>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.16/dist/sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
  <%
    // Check if a success message exists
     message = (String) request.getAttribute("message");
    if (message != null) {
  %>
  Swal.fire({
    title: 'Success!',
    text: '<%= message %>',
    icon: 'success',
    showCancelButton: true,
    confirmButtonText: 'OK',
    cancelButtonText: 'Close',
    reverseButtons: true
  }).then((result) => {
    if (result.isConfirmed) {
      // Redirect to index.jsp after clicking OK
      window.location.href = 'index.jsp';
    }
  });
  <% } %>

  // document.addEventListener('DOMContentLoaded', function() {
  //   // Event listener for form submission
  //   document.getElementById("adminForm").addEventListener("submit", function(event) {
  //     var username = document.getElementById("username");
  //     var password = document.getElementById("password");
  //
  //     // Reset the invalid class to ensure it doesn't persist
  //     username.classList.remove("invalid");
  //     password.classList.remove("invalid");
  //
  //     // Check if either username or password is empty
  //     if (username.value === "" || password.value === "") {
  //       // Prevent form submission
  //       event.preventDefault();
  //
  //       // Apply red border to invalid inputs
  //       if (username.value === "") {
  //         username.classList.add("invalid");
  //       }
  //       if (password.value === "") {
  //         password.classList.add("invalid");
  //       }
  //
  //       // Display SweetAlert to alert the user
  //       Swal.fire({
  //         title: 'Input Required!',
  //         text: 'Please fill in both username and password.',
  //         icon: 'error',
  //         confirmButtonText: 'OK'
  //       });
  //     }
  //   });
  // });
</script>
</body>
</html>


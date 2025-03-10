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

<%
  // Fetch the admin list (if necessary)
  AdminDAOImpl adminDAO = (AdminDAOImpl) AdminDAOFactory.getAdminDAO();
  List<Admin> admins = adminDAO.getAllAdmins();
%>

<% String message = request.getParameter("message");
  if (message != null) { %>
<div class="alert alert-success" role="alert">
  <%= message %>
</div>
<% } %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Registration</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
  <h2 class="my-4">Admin Registration</h2>

  <!-- Admin Registration Form -->
  <div class="mb-4">
    <h4>Register New Admin</h4>
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
          <option value="user">User</option>
          <option value="admin">Admin</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Register Admin</button>
    </form>
  </div>

</body>
</html>


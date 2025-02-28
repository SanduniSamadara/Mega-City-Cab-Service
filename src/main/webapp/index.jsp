<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mega City Cab - Login</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        form { margin-top: 100px; display: inline-block; padding: 20px; border: 1px solid #ccc; }
    </style>
</head>
<body>
<h2>Mega City Cab - Login</h2>
<form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required> <br><br>

    <label>Password:</label>
    <input type="password" name="password" required> <br><br>

    <input type="submit" value="Login">
</form>
</body>
</html>

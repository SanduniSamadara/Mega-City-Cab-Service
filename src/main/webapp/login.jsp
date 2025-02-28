<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Mega City Cab</title>
    <script>
        function showError() {
            const params = new URLSearchParams(window.location.search);
            if (params.has('error')) {
                alert(params.get('error'));
            }
        }
    </script>
</head>
<body onload="showError()">
<h2>Login</h2>
<form action="LoginServlet" method="post">
    <label>Username:</label>
    <input type="text" name="username" required><br><br>
    <label>Password:</label>
    <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>

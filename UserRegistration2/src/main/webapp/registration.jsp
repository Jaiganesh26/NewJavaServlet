<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
    <h1 align="center">User Registration</h1>
    <form action="http://localhost:8080/UserRegistration2/insertDetails" method="post">
        <div align="center">
            <label for="username">UserName:</label>
            <input type="text" id="username" name="username" required><br></br>
        </div>
        <div align="center">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br></br>
        </div>
        <div align="center">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br></br>
        </div>
        <div align="center">
            <button type="submit">Register</button>
        </div>
    </form>
</body>
</html>

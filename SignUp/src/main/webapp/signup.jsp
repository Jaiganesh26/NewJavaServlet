<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
    <h2>Sign Up</h2>
    <form action="signUp" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" required><br>

        <label for="contact">Contact:</label>
        <input type="text" name="contact" pattern="[0-9]+" required><br>

        <label for="nickname">Nickname:</label>
        <input type="text" name="nickname" required><br>

        <input type="submit" value="Sign Up">
    </form>
</body>
</html>

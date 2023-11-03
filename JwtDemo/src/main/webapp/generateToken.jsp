<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generate JWT Token</title>
</head>
<body>
    <h1>Generate JWT Token</h1>
    
    <form action="GenerateTokenServlet" method="get">
        <input type="submit" value="Generate Token">
    </form>
    
    <h2>JWT Token:</h2>
    <pre>
        ${token}
    </pre>
</body>
</html>

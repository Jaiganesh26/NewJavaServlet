<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Success</title>
</head>

<body>
    <h1>Registration Error!</h1>

    <%-- Printing input field values --%>
    <% String firstName = request.getParameter("firstName"); %>
    <% String email = request.getParameter("email"); %>
    <% String password = request.getParameter("password"); %>
    <% String lastName = request.getParameter("lastName"); %>
    <% String address = request.getParameter("address"); %>
    <% String city = request.getParameter("city"); %>
    <% String state = request.getParameter("state"); %>
    <% String zipCode = request.getParameter("zipCode"); %>
    <% String phoneNumber = request.getParameter("phoneNumber"); %>
    <% String dob = request.getParameter("dob"); %>

    <p>First Name: <%= firstName %></p>
    <p>Email: <%= email %></p>
    <p>Password: <%= password %></p>
    <p>Last Name: <%= lastName %></p>
    <p>Address: <%= address %></p>
    <p>City: <%= city %></p>
    <p>State: <%= state %></p>
    <p>Zip Code: <%= zipCode %></p>
    <p>Phone Number: <%= phoneNumber %></p>
    <p>Date of Birth: <%= dob %></p>

    <!-- Additional content or redirection logic -->
</body>

</html>

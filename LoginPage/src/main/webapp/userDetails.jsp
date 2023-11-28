<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Center-align the content */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
        }
          nav h2 {
	            margin-left: 16px;
            	color: white;
                 }
        /* Style the user details */
        .details-container {
            background: rgba(3, 3, 55, 0.5);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 10px rgba(0, 0, 0, 0.9);
            color: white;
        }
        nav {
	width: 100%;
	height: 60px;
	/* padding: 0.5% 0px; */
	background: #21539e;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
}
    </style>
</head>
<body>
<nav>
        <a href="login.jsp"><h2>Login</h2></a>
    </nav>
    <div class="container">
        <div class="details-container">
            <h1>User Details</h1>
            <p>First Name: <%= request.getAttribute("firstName") %></p>
            <p>Last Name: <%= request.getAttribute("lastName") %></p>
            <p>Email: <%= request.getAttribute("email") %></p>
            <p>Address: <%= request.getAttribute("address") %></p>
            <p>City: <%= request.getAttribute("city") %></p>
            <p>State: <%= request.getAttribute("state") %></p>
            <p>ZIP Code: <%= request.getAttribute("zipCode") %></p>
            <p>Phone Number: <%= request.getAttribute("phoneNumber") %></p>
            <p>Date Of Birth: <%= request.getAttribute("dob") %></p>
            <img src="<%=request.getContextPath()%>/image/<%= request.getAttribute("imageFileName") %>" style="width:150px;height:150px">
        </div>
    </div>
</body>
</html>

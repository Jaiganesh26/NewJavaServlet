<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Center-align the form */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Style the form */
        .form-container {
            background-color: #f3f3f3;
            padding: 200px;
            padding-top:10px;
            padding-bottom:10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Style form inputs and labels */
        .form-group {
            margin: 10px 0;
        }

        label {
            display: block;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1>Registration</h1>
            <form action="RegisterServlet" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" required>
                </div>
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" name="firstName" id="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" name="lastName" id="lastName">
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" name="address" id="address">
                </div>
                <div class="form-group">
                    <label for="city">City:</label>
                    <input type="text" name="city" id="city">
                </div>
                <div class="form-group">
                    <label for="state">State:</label>
                    <input type="text" name="state" id="state">
                </div>
                <div class="form-group">
                    <label for="zipCode">ZIP Code:</label>
                    <input type="text" name="zipCode" id="zipCode">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" name="phoneNumber" id="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" name="dob" id="dob">
                </div><br>
                <div class="form-group">
                   <center><input type="submit" value="Register"></center> 
                </div>
            </form>
        </div>
    </div>
</body>
</html>

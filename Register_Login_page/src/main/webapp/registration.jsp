<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your external CSS file -->
</head>
<body>
    <div id="container">
        <header>Registration</header><br>
        <form action="RegisterServlet" method="post">
            <fieldset>
                <input type="email" name="email" id="email" placeholder="Email" required>
            </fieldset>
            <fieldset>
                <input type="password" name="password" id="password" placeholder="Password" required>
            </fieldset>
            <fieldset>
                <input type="text" name="firstName" id="firstName" placeholder="First Name">
            </fieldset>
            <fieldset>
                <input type="text" name="lastName" id="lastName" placeholder="Last Name">
            </fieldset>
            <fieldset>
                <input type="text" name="address" id="address" placeholder="Address">
            </fieldset>
            <fieldset>
                <input type="text" name="city" id="city" placeholder="City">
            </fieldset>
            <fieldset>
                <input type="text" name="state" id="state" placeholder="State">
            </fieldset>
            <fieldset>
                <input type="text" name="zipCode" id="zipCode" placeholder="ZIP Code">
            </fieldset>
            <fieldset>
                <input type="tel" name="phoneNumber" id="phoneNumber" placeholder="Phone Number">
            </fieldset>
            <fieldset>
                <input type="date" name="dob" id="dob" placeholder="Date of Birth">
            </fieldset><br>
            <fieldset>
                <input type="submit" value="Register">
            </fieldset>
        </form>
    </div>
</body>
</html>

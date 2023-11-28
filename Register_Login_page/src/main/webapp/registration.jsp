<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="style2.css"> <!-- Link to your external CSS file -->
</head>
<body>
    <div class="container">
        <div class="form-container registration-card">
            <h1 class="header">Registration</h1>
            <form action="RegisterServlet" method="post">
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="email" name="email" id="email" placeholder="Email" required>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-lock"></i>
                    </div>
                    <input type="password" name="password" id="password" placeholder="Password" required>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="text" name="firstName" id="firstName" placeholder="First Name">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="text" name="lastName" id="lastName" placeholder="Last Name">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-map-marker"></i>
                    </div>
                    <input type="text" name="address" id="address" placeholder="Address">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-city"></i>
                    </div>
                    <input type="text" name="city" id="city" placeholder="City">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-flag"></i>
                    </div>
                    <input type="text" name="state" id="state" placeholder="State">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-barcode"></i>
                    </div>
                    <input type="text" name="zipCode" id="zipCode" placeholder="ZIP Code">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-phone"></i>
                    </div>
                    <input type="tel" name="phoneNumber" id="phoneNumber" placeholder="Phone Number">
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-calendar-alt"></i>
                    </div>
                    <input type="date" name="dob" id="dob" placeholder="Date of Birth">
                </div>
                <div class="form-group form-field">
                    <input type="submit" value="Register">
                </div>
            </form>
          
            <div>
                Already have an account? <a href="login.jsp">Login</a>
            </div>
        </div>
    </div>
</body>
</html>

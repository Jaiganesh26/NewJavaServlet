<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to your external CSS file -->
</head>
<body>
    <div class="container">
        <div class="form-container login-card">
            <h1 class "header">
                <h2>Login</h2><br><br>
            </h1>
            <form action="LoginServlet" method="post">
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
                    <input type="submit" value="Login">
                </div>
            </form>
          
           <div>
        Don't have an account? <a href="registration.jsp">Sign Up Now</a>
      </div>
        </div>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Styles for the login page */
        body {
            background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding-left:10px;
            padding-right:10px;
            padding-top:30px;
            padding-bottom:30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }

        .header {
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-top: 7px;
            position: relative;
        }

        .form-field {
            position: relative;
        }

        .icon {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: 30px;
            color: #999;
        }

        .icon i {
            font-size: 18px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            padding-left: 40px;
            width: calc(100% - 40px);
            border-radius: 4px;
            border: 1px solid #ccc;
            height: 50px;
            box-sizing: border-box;
        }

        .password-toggle {
            position: absolute;
            top: 50%;
            right: 40px;
            transform: translateY(-50%);
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container registration-card">
            <h1 class="header">Login</h1>
            <form action="LoginServlet" method="post">
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-envelope"></i>
                    </div>
                    <input type="email" name="email" id="email" placeholder="Email" required>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-lock"></i>
                    </div>
                    <input type="password" name="password" id="password" placeholder="Password" required>
                    <span class="password-toggle" onclick="togglePassword()">
                        <i id="toggleIcon" class="fas fa-eye"></i>
                    </span>
                </div>
                 <% String errorMessage = (String) request.getAttribute("errorMessage");
                 if (errorMessage != null && !errorMessage.isEmpty()) { %>
                 <p style="color: red;"><%= errorMessage %></p>
                 <% } %>
                <div class="form-group form-field">
                    <input type="submit" value="Login">
                </div>
            </form><br>
          
            <div>
                Don't have an account? <a href="registration.jsp">Register</a>
            </div>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordField = document.getElementById('password');
            const toggleIcon = document.getElementById('toggleIcon');
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                toggleIcon.classList.remove('fa-eye');
                toggleIcon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                toggleIcon.classList.remove('fa-eye-slash');
                toggleIcon.classList.add('fa-eye');
            }
        }
        
    </script>
</body>
</html>

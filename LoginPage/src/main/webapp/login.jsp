<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
            padding: 50px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.9);
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
        input[type="text"] {
            width: 100%;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
        }
        
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1>Login</h1>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" required>
                </div><br>
               <div class="form-group">
                  <input type="submit" value="Login">
                </div>
            </form>
            <!-- Add a "Register" button here -->
            <div class="form-group">
                <a href="registration.jsp">
                    <button type="button" style="position:absolute;top:415px;right:590px;color:#007bff;border-radius: 20px;padding: 5px 10px;cursor: pointer;">Register</button>
                </a>
            </div>
        </div>
    </div>
</body>
</html>

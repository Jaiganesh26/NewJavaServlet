<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registration</title>
<style>
    body {
        background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
        font-family: 'Poppins', sans-serif;
    }

    #form {
        width: 300px;
        margin: 20vh auto 0 auto;
        padding: 20px;
        background-color: whitesmoke;
        border-radius: 4px;
        font-size: 12px;
    }
    #form h1 {
        color: #0f2027;
        text-align: center;
    }

    #form button {
        padding: 10px;
        margin-top: 10px;
        width: 100%;
        color: white;
        background-color: rgb(41, 57, 194);
        border: none;
        border-radius: 4px;
    }

    .input-control {
        display: flex;
        flex-direction: column;
    }

    .input-control input {
        border: 2px solid #f0f0f0;
        border-radius: 4px;
        display: block;
        font-size: 12px;
        padding: 10px;
        width: 90%;
    }

    .input-control input:focus {
        outline: 0;
    }

    .input-control.success input {
        border-color: #09c372;
    }

    .input-control.error input {
        border-color: #ff3860;
    }	

    .input-control .error {
        color: #ff3860;
        font-size: 9px;
        height: 13px;
    }
</style>
</head>

<body>
    <div class="container">
        <form id="form" action="RegisterServlet" method="post" enctype="multipart/form-data" onsubmit="return validateInputs()">
            <h1>Registration</h1>
            <div class="input-control">
                <label for="firstName">FirstName</label>
                <input id="firstName" name="firstName" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="email">Email</label>
                <input id="email" name="email" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="password">Password</label>
                <input id="password" name="password" type="password">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="lastName">LastName</label>
                <input id="lastName" name="lastName" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="address">Address</label>
                <input id="address" name="address" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="city">City</label>
                <input id="city" name="city" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="state">State</label>
                <input id="state" name="state" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="zipCode">ZipCode</label>
                <input id="zipCode" name="zipCode" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="phoneNumber">PhoneNumber</label>
                <input id="phoneNumber" name="phoneNumber" type="text">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="dob">Date of Birth</label>
                <input id="dob" name="dob" type="date">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="image">Image</label>
                <input id="image" name="image" type="file">
                <div class="error"></div>
            </div>
            <button type="submit">Sign Up</button><br><br>
            <div>
                Already have an account? <a href="login.jsp">Login</a>
            </div>
        </form>

        <script>
            function validateInputs() {
                var inputs = document.getElementsByTagName("input");
                var isValid = true;

                for (var i = 0; i < inputs.length; i++) {
                    if (inputs[i].type === "text" || inputs[i].type === "password" || inputs[i].type === "file" || inputs[i].type === "date") {
                        if (inputs[i].value.trim() === "") {
                            inputs[i].parentNode.classList.add("error");
                            isValid = false;
                        } else {
                            inputs[i].parentNode.classList.remove("error");
                        }
                    }
                }

                return isValid;
            }
        </script>
    </div>
</body>

</html>
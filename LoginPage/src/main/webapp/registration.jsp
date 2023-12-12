<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
<style>
body {
    background: linear-gradient(to right,#0f2027, #203a43, #2c5364);
    font-family: 'Poppins', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
}
.icon i {
    font-size: 18px;
}
.container {
    background-color: #fff;
    padding: 40px;
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
    margin-top:7px;
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
input[type="text"],
input[type="email"],
input[type="password"],
input[type="tel"],
input[type="date"],
input[type="file"] {
    padding-left: 40px;
    width: calc(100% - 40px);
    border-radius: 4px;
    border: 1px solid #ccc;
    height: 40px;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

a {
    color: #007bff;
    text-decoration: none;
}
input[type="file"] {
    padding-left: 30px;
    width: calc(100% - 40px);
    border-radius: 4px;
    border: 1px solid #ccc;
    height: 40px;
    box-sizing: border-box;
    position: relative;
    overflow: hidden;
}
        .password-toggle {
            position: absolute;
            top: 50%;
            right: 40px;
            transform: translateY(-50%);
            cursor: pointer;
        }
input[type="file"]::before {
    content: 'Upload Image'; /* Displayed text */
    display: block;
    position: absolute;
    top: 50%;
    left: 15%;
    transform: translate(-50%, -50%);
    background-color: #0056b3;
    color: white;
    padding: 5px 5px;
    border-radius: 4px;
    cursor: pointer;
}
input[type="file"]::-webkit-file-upload-button {
    visibility: hidden;
}
.error input {
        border-color: #ff3860;
    }
.success input {
    border-color: #09c372;
    }
    .coming-error {
        color: #ff3860;
        font-size: 12px;
    }
 </style> 
 <script>
 function validateInputs() {
     var inputs = document.getElementsByTagName("input");
     var isValid = true;

     var errorMessages = {
         email: "Provide a valid email address",
         password: "Please enter a password",
         firstName: "Please enter your first name",
         lastName: "Please enter your last name",
         address: "Please enter your address",
         city: "Please enter your city",
         state: "Please enter your state",
         zipCode: "Please enter your ZIP code",
         phoneNumber: "Please enter valid phone number",
         dob: "Please enter your date of birth",
         image: "Please upload an image"
     };

     for (var i = 0; i < inputs.length; i++) {
         var field = inputs[i].id;
         if (errorMessages.hasOwnProperty(field)) {
             if (inputs[i].value.trim() === "") {
                 setErrorMessage(inputs[i], errorMessages[field]);
                 isValid = false;
             } else {
                 if (field === "email" && !isValidEmail(inputs[i].value.trim())) {
                     setErrorMessage(inputs[i], errorMessages[field]);
                     isValid = false;
                 } else if (field === "phoneNumber" && !isValidPhoneNumber(inputs[i].value.trim())) {
                     setErrorMessage(inputs[i], errorMessages[field]);
                     isValid = false;
                 } else {
                     setSuccess(inputs[i]);
                 }
             }
         }
     }

     return isValid;
 }

 function setErrorMessage(input, message) {
     input.parentNode.classList.add("error");
     input.parentNode.classList.remove("success");
     var errorDiv = input.parentNode.querySelector(".error-msg");
     errorDiv.textContent = message;
     errorDiv.classList.add("coming-error");
 }

 function setSuccess(input) {
     input.parentNode.classList.remove("error");
     input.parentNode.classList.add("success");
     var errorDiv = input.parentNode.querySelector(".error-msg");
     errorDiv.textContent = "";
     errorDiv.classList.remove("coming-error");
 }

 function clearErrorMessage(input) {
     input.parentNode.classList.remove("error");
     input.parentNode.classList.add("success");
     var errorDiv = input.parentNode.querySelector(".error-msg");
     errorDiv.textContent = "";
     errorDiv.classList.remove("coming-error");
 }

 function isValidEmail(email) {
     return /\S+@\S+\.\S+/.test(email);
 }
 function isValidPhoneNumber(phoneNumber) {
     return /^\d{10}$/.test(phoneNumber);
 }
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
</head>
<body>
    <div class="container">
            <h1 class="header">Registration</h1>
         <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <% } %>
            <form action="RegisterServlet" method="post" enctype="multipart/form-data" onsubmit="return validateInputs()">
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-envelope"></i>
                    </div>
                    <input type="text" name="email" id="email" placeholder="Email" >
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-lock"></i>
                    </div>
                    <input type="password" name="password" id="password" placeholder="Password" >
                        <div class="error-msg"></div>
                        <span class="password-toggle" onclick="togglePassword()">
                        <i id="toggleIcon" class="fas fa-eye"></i>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="text" name="firstName" id="firstName" placeholder="First Name">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="text" name="lastName" id="lastName" placeholder="Last Name">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-map-marker"></i>
                    </div>
                    <input type="text" name="address" id="address" placeholder="Address">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-city"></i>
                    </div>
                    <input type="text" name="city" id="city" placeholder="City">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-flag"></i>
                    </div>
                    <input type="text" name="state" id="state" placeholder="State">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-barcode"></i>
                    </div>
                    <input type="text" name="zipCode" id="zipCode" placeholder="ZIP Code">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-phone"></i>
                    </div>
                    <input type="tel" name="phoneNumber" id="phoneNumber" placeholder="Phone Number">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <div class="icon">
                        <i class="fas fa-calendar-alt"></i>
                    </div>
                    <input type="date" name="dob" id="dob" placeholder="Date of Birth">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-fields">
                    <input type="file" name="image" id="image">
                    <div class="error-msg"></div>
                </div>
                <div class="form-group form-field">
                    <input type="submit" value="Sign up">
                </div>
            </form><br>         
            <div>
                Already have an account? <a href="login.jsp">Login</a>
            </div>
        </div>
</body>
</html>
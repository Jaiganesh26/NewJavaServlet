<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <script>
    function setError(element, message) {
        const inputControl = element.parentElement;
        const errorDisplay = inputControl.querySelector('.error');
        errorDisplay.innerText = message;
        inputControl.classList.add('error');
        inputControl.classList.remove('success');
    }

    function setSuccess(element) {
        const inputControl = element.parentElement;
        const errorDisplay = inputControl.querySelector('.error');
        errorDisplay.innerText = '';
        inputControl.classList.add('success');
        inputControl.classList.remove('error');
    }

    function isValidEmail(email) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    function validateInputs() {
        const firstName = document.getElementById('firstName');
        const email = document.getElementById('email');
        const password = document.getElementById('password');
        const lastName = document.getElementById('lastName');
        const address = document.getElementById('address');
        const city = document.getElementById('city');
        const state = document.getElementById('state');
        const zipCode = document.getElementById('zipCode');
        const phoneNumber = document.getElementById('phoneNumber');
        const dob = document.getElementById('dob');
        
        console.log('First Name:', firstName.value);
        console.log('Email:', email);
        console.log('Password:', password);
        console.log('Last Name:', lastName);
        console.log('Address:', address);
        console.log('City:', city);
        console.log('State:', state);
        console.log('Zip Code:', zipCode);
        console.log('Phone Number:', phoneNumber);
        console.log('Date of Birth:', dob);

        const firstNameValue = firstName.value.trim();
        const emailValue = email.value.trim();
        const passwordValue = password.value.trim();
        const lastNameValue = lastName.value.trim();
        const addressValue = address.value.trim();
        const cityValue = city.value.trim();
        const stateValue = state.value.trim();
        const zipCodeValue = zipCode.value.trim();
        const phoneNumberValue = phoneNumber.value.trim();
        const dobValue = dob.value.trim();

        // Log the values to the console for verification
        console.log('First Name:', firstNameValue);
        console.log('Email:', emailValue);
        console.log('Password:', passwordValue);
        console.log('Last Name:', lastNameValue);
        console.log('Address:', addressValue);
        console.log('City:', cityValue);
        console.log('State:', stateValue);
        console.log('Zip Code:', zipCodeValue);
        console.log('Phone Number:', phoneNumberValue);
        console.log('Date of Birth:', dobValue);


        if (firstNameValue === '') {
            setError(firstName, 'FirstName is required');
            return false;
        } else {
            setSuccess(firstName);
        }

        if (emailValue === '') {
            setError(email, 'Email is required');
            return false;
        } else if (!isValidEmail(emailValue)) {
            setError(email, 'Provide a valid email address');
            return false;
        } else {
            setSuccess(email);
        }

        if (passwordValue === '') {
            setError(password, 'Password is required');
            return false;
        } else if (passwordValue.length < 8) {
            setError(password, 'Password must be at least 8 characters');
            return false;
        } else {
            setSuccess(password);
        }

        if (lastNameValue === '') {
            setError(lastName, 'LastName is required');
            return false;
        } else {
            setSuccess(lastName);
        }

        if (addressValue === '') {
            setError(address, 'Address is required');
            return false;
        } else {
            setSuccess(address);
        }

        if (cityValue === '') {
            setError(city, 'City is required');
            return false;
        } else {
            setSuccess(city);
        }

        if (stateValue === '') {
            setError(state, 'State is required');
            return false;
        } else {
            setSuccess(state);
        }

        if (zipCodeValue === '') {
            setError(zipCode, 'ZipCode is required');
            return false;
        } else {
            setSuccess(zipCode);
        }

        if (phoneNumberValue === '') {
            setError(phoneNumber, 'PhoneNumber is required');
            return false;
        } else {
            setSuccess(phoneNumber);
        }

        if (dobValue === '') {
            setError(dob, 'Date Of Birth is required');
            return false;
        } else {
            setSuccess(dob);
        }
        const isValid = true;
        console.log('Is Valid:', isValid); // Print the return value
        return isValid;
}
</script>
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
    </div>
</body>

</html>
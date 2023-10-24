document.addEventListener("DOMContentLoaded", function () {
    var loginForm = document.getElementById("loginForm");
    var registrationPopup = document.getElementById("registrationPopup");
    var registerLink = document.getElementById("registerLink");
    var resultDiv = document.getElementById("result");

    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        // Perform client-side validation (optional)

        // Send an AJAX request to the server for login
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "LoginServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = xhr.responseText;
                resultDiv.innerHTML = response;
            }
        };
        xhr.send("username=" + username + "&password=" + password);
    });

    registerLink.addEventListener("click", function (event) {
        event.preventDefault();

        // Display the registration popup
        registrationPopup.style.display = "block";

        // Load the registration form using AJAX (not shown here)
    });
});

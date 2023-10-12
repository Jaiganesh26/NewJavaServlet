<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
    <!-- Include the Select2 CSS and JavaScript files -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        
        center {
            margin-top: 50px;
        }
        
        p {
            font-size: 18px;
        }
        
        select.js-select2 {
            width: 15%;
            padding: 10px;
            font-size: 16px;
        }
        
        #funcdemo {
            font-size: 18px;
            margin-top: 20px;
        }
    </style>
</head>

<body>
    <center>
        <br><br><br><br><br><br>
        <p>Search or Select a Company from the list:</p>

        <select id="myList" class="js-select2">
        <option value="Company"></option>
            <option value="TCS">TCS</option>
            <option value="Thapovan">Thapovan</option>
            <option value="Infosys">Infosys</option>
            <option value="Amazon">Amazon</option>
        </select>

        <p>Select a Location:</p>

        <select id="LocationList">
            <!-- Options for frameworks will be populated based on the selection in the first field using JavaScript. -->
        </select>

        <p>Email-ID:</p>

        <select id="EmailList">
            <!-- Options for libraries will be populated based on the selection in the first field using JavaScript. -->
        </select>

        <p id="funcdemo"></p>

        <script>
            $(document).ready(function() {
                // Initialize Select2 for the first dropdown
                $("#myList").select2();

                $("#myList").on("change", function() {
                    populateLocationsAndEmails();
                    myFunctiondemo();
                });

                function populateLocationsAndEmails() {
                    var selectedCompany = $("#myList").val();
                    var locationList = $("#LocationList");
                    var emailList = $("#EmailList");
                    // Clear the existing options
                    locationList.empty();
                    emailList.empty();

                    // AJAX call to fetch data from the server
                    $.ajax({
                        url: "http://localhost:8080/AutoLoadField/companyDataServlet",
                        type: "Get",
                        data: {
                            company: selectedCompany
                        },
                        success: function(data) {
                        	console.log(data);
                        	try {
                            // Parse the JSON data returned from the servlet
                            var companyData = JSON.parse(data);
                            console.log(companyData);
                            var locations = companyData.locations;
                            var emails = companyData.emails;
                        	
                            // Populate the dropdown lists
                            $.each(locations, function(key,value) {
                                locationList.append(new Option(value,key));
                            });

                            $.each(emails, function(key, value) {
                                emailList.append(new Option(value, key));
                            }); } catch (e) {
                                console.error("Error parsing JSON:", e);
                            }
                        }
                    });
                }

                function myFunctiondemo() {
                    var x = $("#myList").val();
                    $("#funcdemo").text("The new selection is: " + x);
                }

                // Populate frameworks and libraries initially when the page loads.
                populateLocationsAndEmails();
            });
        </script>
    </center>
</body>

</html>


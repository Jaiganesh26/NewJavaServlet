<!DOCTYPE html>
<html>
<head>
    <title>Autocomplete Form</title>
    <script src="https://jsuites.net/v4/jsuites.js"></script>
    <link rel="stylesheet" href="https://jsuites.net/v4/jsuites.css" type="text/css" />
    <style>
        .full-page {
            height: 100%;
            width: 100%;
            background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)));
            background-position: center;
            background-size: cover;
            position: absolute;
        }

        * {
            box-sizing: border-box;
        }

        body {
            font: 16px Arial;
        }

        .autocomplete {
            position: relative;
            display: inline-block;
        }

        input {
            border: 1px solid transparent;
            background-color: #f1f1f1;
            padding: 10px;
            font-size: 16px;
        }

        input[type=text] {
            background-color: #f1f1f1;
            width: 100%;
        }

        input[type=submit] {
            background-color: DodgerBlue;
            color: #fff;
            cursor: pointer;
        }

        .autocomplete-items {
            position: absolute;
            border: 1px solid #d4d4d4;
            border-bottom: none;
            border-top: none;
            z-index: 99;
            top: 100%;
            left: 0;
            right: 0;
        }

        .autocomplete-items div {
            padding: 10px;
            cursor: pointer;
            background-color: #fff;
            border-bottom: 1px solid #d4d4d4;
        }

        .autocomplete-items div:hover {
            background-color: #e9e9e9;
        }

        .autocomplete-active {
            background-color: DodgerBlue !important;
            color: #ffffff;
        }
    </style>
</head>
<body>
    <div class="full-page">
        <form id="myForm">
            <center>
                <h2>Autocomplete Form</h2>
                <br /><br />
                <div class="autocomplete" id="Company dropdown" placeholder="Company"></div>
                <br /><br />
                <div class="autocomplete" id="drop" placeholder="Location"></div>
                <br /><br />
                <div class="autocomplete" id="country" placeholder="Email"></div>
                <br /><br /><br /><br />
                <center>
                    <input type="submit" value="Submit">
                </center>
            </center>
        </form>
    </div>
    <script>
        const dropdownCompany = jSuites.dropdown(document.getElementById('Company dropdown'), {
            data: [{
                group: 'IT Companies',
                value: '1',
                text: 'Thapovan'
            }, {
                group: 'IT Companies',
                value: '2',
                text: 'TCS'
            }, {
                group: 'IT Companies',
                value: '4',
                text: 'Infosys'
            }, {
                group: 'IT Companies',
                value: '5',
                text: 'Cognizant'
            }, {
                group: 'IT Companies',
                value: '7',
                text: 'ZOHO'
            }, {
                group: 'Automobile Companies',
                value: '8',
                text: 'Bharat Benz'
            }, {
                group: 'Automobile Companies',
                value: '10',
                text: 'Ashok Leyland'
            }, {
                group: 'Automobile Companies',
                value: '11',
                text: 'Hyundai'
            }],
            width: '280px',
            autocomplete: true
        });

        const dropdownLocation = jSuites.dropdown(document.getElementById('drop'), {
            data: [{
                group: 'Location',
                value: '1',
                text: 'Kodampakkam'
            }, {
                group: 'Location',
                value: '2',
                text: 'Chennai'
            }, {
                group: 'Location',
                value: '4',
                text: 'Bangalore'
            }, {
                group: 'Location',
                value: '5',
                text: 'Tambaram Sanitorium'
            }, {
                group: 'Location',
                value: '7',
                text: 'Potheri'
            }, {
                group: 'Location',
                value: '8',
                text: 'Padapai'
            }, {
                group: 'Location',
                value: '10',
                text: 'Chennai'
            }, {
                group: 'Location',
                value: '11',
                text: 'Kolkata'
            }],
            width: '280px',
            autocomplete: true
        });

        const dropdownEmail = jSuites.dropdown(document.getElementById('country'), {
            data: [{
                group: 'Email Address',
                value: '1',
                text: 'Thapovan@gmail.com'
            }, {
                group: 'Email Address',
                value: '2',
                text: 'Tcs2768@gmail.com'
            }, {
                group: 'Email Address',
                value: '4',
                text: 'infosys@gmail.com'
            }, {
                group: 'Email Address',
                value: '5',
                text: 'cognizant@gmail.com'
            }, {
                group: 'Email Address',
                value: '7',
                text: 'zohoSchools@gmail.com'
            }, {
                group: 'Email Address',
                value: '8',
                text: 'bharatbenz@gmail.com'
            }, {
                group: 'Email Address',
                value: '10',
                text: 'ashokleyland@gmail.com'
            }, {
                group: 'Email Address',
                value: '11',
                text: 'hyundai@gmail.com'
            }],
            width: '280px',
            autocomplete: true
        });

        // Function to handle form submission
        document.getElementById('myForm').addEventListener('submit', function (e) {
            e.preventDefault(); // Prevent the default form submission
 
            const selectedCompany = dropdownCompany.getValue();
            const selectedLocation = dropdownLocation.getValue();
            const selectedEmail = dropdownEmail.getValue();
            
            alert(('Selected Company:', selectedCompany),
                 ('Selected Location:', selectedLocation),
                  ('Selected Email:', selectedEmail));

             window.location.href ='thankyou.html';
        });
    </script>
</body>
</html>

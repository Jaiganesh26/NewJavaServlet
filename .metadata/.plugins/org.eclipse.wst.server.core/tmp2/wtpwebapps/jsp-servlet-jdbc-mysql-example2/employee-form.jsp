<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.model.Employee" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Employee Management Application </a>
            </div>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <%
                if (request.getAttribute("employee") != null) {
                %>
                    <form action="<%= request.getContextPath() %>/update" method="POST">
                        <input type="hidden" name="_method" value="PUT">
                <%
                } else {
                %>
                    <form action="<%= request.getContextPath() %>/insert" method="POST">
                <%
                }
                %>
                <caption>
                    <h2>
                        <%
                        if (request.getAttribute("employee") == null) {
                        %>
                            Add New Employee
                        <%
                        } else {
                        %>
                            Update Employee
                        <%
                        }
                        %>
                    </h2>
                </caption>
                <fieldset class="form-group">
                <input type="hidden" name="Employee_No" value="${employee != null ? employee.getEmployee_No() : ''}" />
                    <label>First Name</label>
                    <input type="text" value="${employee != null ? employee.getFirst_Name() : ''}" class="form-control" name="First_Name" />
                </fieldset>
                <fieldset class="form-group">
                    <label>Last Name</label>
                    <input type="text" value="${employee != null ? employee.getLast_Name() : ''}" class="form-control" name="Last_Name" />
                </fieldset>
                <fieldset class="form-group">
                    <label>Designation</label>
                    <input type="text" value="${employee != null ? employee.getDesignation() : ''}" class="form-control" name="Designation" />
                </fieldset>
                <fieldset class="form-group">
                    <label>DOB</label>
                    <input type="text" value="${employee != null ? employee.getDOB() : ''}" class="form-control" name="DOB" />
                </fieldset>
                <fieldset class="form-group">
                    <label>Email Id</label>
                    <input type="text" value="${employee != null ? employee.getEmail() : ''}" class="form-control" name="Email" />
                </fieldset>
                <fieldset class="form-group">
                    <label>Mobile Number</label>
                    <input type="text" value="${employee != null ? employee.getMobile_No() : ''}" class="form-control" name="Mobile_No" />
                </fieldset>
                <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

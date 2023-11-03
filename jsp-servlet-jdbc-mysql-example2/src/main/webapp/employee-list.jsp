<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Employee" %> <!-- Import the List class -->
<!DOCTYPE html>
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
                <a href="https://www.javaguides.net" class="navbar-brand"> Employee Management </a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="container">
        <h3 class="text-center">List of Employees</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Employee</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Employee_No</th>
                    <th>First_Name</th>
                    <th>Last_Name</th>
                    <th>Designation</th>
                    <th>DOB</th>
                    <th>Email</th>
                    <th>Mobile_No</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Employee> listEmployee = (List<Employee>) request.getAttribute("listEmployee");
                    if (listEmployee != null) {
                        for (Employee employee : listEmployee) {
                %>
                    <tr>
                        <td><%= employee.getEmployee_No() %></td>
                        <td><%= employee.getFirst_Name() %></td>
                        <td><%= employee.getLast_Name() %></td>
                        <td><%= employee.getDesignation() %></td>
                        <td><%= employee.getDOB() %></td>
                        <td><%= employee.getEmail() %></td>
                        <td><%= employee.getMobile_No() %></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/edit?Employee_No=<%= employee.getEmployee_No() %>" class="btn btn-primary">Edit</a>
                            <a href="<%=request.getContextPath()%>/delete?Employee_No=<%= employee.getEmployee_No() %>" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

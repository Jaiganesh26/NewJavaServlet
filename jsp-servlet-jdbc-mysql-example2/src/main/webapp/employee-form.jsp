<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
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

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:choose>
                    <c:when test="${employee != null}">
                        <form action="${pageContext.request.contextPath}/update" method="post">
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/insert" method="post">
                    </c:otherwise>
                </c:choose>

                <caption>
                    <h2>
                        <c:choose>
                            <c:when test="${employee == null}">
                                Add New Employee
                            </c:when>
                        </c:choose>
                    </h2>
                </caption>

                <c:choose>
                    <c:when test="${employee != null}">
                        <input type="text" name="Employee_No" value="${employee.Employee_No}" />
                    </c:when>
                </c:choose>

                <fieldset class="form-group">
                    <label>First Name</label> <input type="text" value="${employee != null ? employee.First_Name : ''}"
                        class="form-control" name="First_Name" required="required" />
                </fieldset>

                <fieldset class="form-group">
                    <label>Last Name</label> <input type="text" value="${employee != null ? employee.Last_Name : ''}"
                        class="form-control" name="Last_Name" />
                </fieldset>

                <fieldset class="form-group">
                    <label>Designation</label> <input type="text" value="${employee != null ? employee.Designation : ''}"
                        class="form-control" name="Designation" />
                </fieldset>

                <fieldset class="form-group">
                    <label>DOB</label> <input type="text" value="${employee != null ? employee.DOB : ''}"
                        class="form-control" name="DOB" />
                </fieldset>

                <fieldset class="form-group">
                    <label>Email Id</label> <input type="text" value="${employee != null ? employee.Email : ''}"
                        class="form-control" name="Email" />
                </fieldset>

                <fieldset class="form-group">
                    <label>Mobile Number</label> <input type="text" value="${employee != null ? employee.Mobile_No : ''}"
                        class="form-control" name="Mobile_No" />
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>

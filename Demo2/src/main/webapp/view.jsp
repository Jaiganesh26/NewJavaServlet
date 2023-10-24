<%@ page isELIgnored="false" language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<title>View</title>
		<link rel="stylesheet" href="style.css"/>
	</head>
<body>
    <nav>
        <h2>All Member</h2>
        <a href="Save">Add Members</a>
    </nav>
    <main>
        <table border="1">
            <tr class="headings">
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Age</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach var="member" items="${list}">
                <tr>
                    <td>${member.id}</td>
                    <td>${member.name}</td> <!-- Use ${member.name} directly -->
                    <td>${member.email}</td> <!-- Use ${member.email} directly -->
                    <td>${member.age}</td> <!-- Use ${member.age} directly -->
                    <td>
                        <a href="EditServlet?id=${member.id}">Edit</a>
                    </td>
                    <td>
                        <a href="Delete?id=${member.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>

</html>

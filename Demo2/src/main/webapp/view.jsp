<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>View</title>
    <link rel="stylesheet" href="style.css" />
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
            <% 
                java.util.List members = (java.util.List) request.getAttribute("list");
                if (members != null) {
                    for (int i = 0; i < members.size(); i++) {
                        entity.Member member = (entity.Member) members.get(i);
            %>
                <tr>
                    <td><%= member.getId() %></td>
                    <td><%= member.getName() %></td>
                    <td><%= member.getEmail() %></td>
                    <td><%= member.getAge() %></td>
                    <td>
                        <a href="EditServlet?id=<%= member.getId() %>">Edit</a>
                    </td>
                    <td>
                        <a href="Delete?id=<%= member.getId() %>">Delete</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </main>
</body>
</html>

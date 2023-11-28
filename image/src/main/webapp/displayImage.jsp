<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Display Image</title>
</head>
<body>
    <h1 style="color:red" align="center">DISPLAY IMAGE DETAIL</h1>

    <div align="center">

        <form action="DisplayServlet" method="post">
            Enter Image Id :
            <input type="number" name="imageId">
            <input type="submit" value="Display Image">
        </form>

    </div>

    <hr>

    <%
        String imgFileName = (String) request.getAttribute("img");
        String imgId = (String) request.getAttribute("id");

        try {
            // Existing code
            System.out.println("Image details retrieved successfully: " + imgFileName);
        } catch (Exception e) {
            System.out.println("Error retrieving image details: " + e.getMessage());
            e.printStackTrace();
        }

        if (imgFileName != null && imgId != null) {
    %>

    <div align="center">
        <table border="1" style="width:600px">
            <tr>
                <th>Image Id </th>
                <th>Image</th>
            </tr>

            <tr>
                <td><%=imgId %></td>
                <td><img src="<%=request.getContextPath()%>/image/<%=imgFileName%>" style="width:250px;height:250px"></td>
            </tr>

        </table>
    </div>

    <%
        } else {
    %>
    <div align="center">
        <p style="color: red;">Image not found.</p>
    </div>
    <%
        }
    %>

</body>
</html>

package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // Set the content type to JSON

        try {
            Connection conn = JDBC_Connection.getConnection();

            // Create a CallableStatement for the stored procedure
            CallableStatement stmt = conn.prepareCall("{call get_AllUsers}");

            // Execute the stored procedure
            ResultSet resultSet = stmt.executeQuery();

            // Process the result set and retrieve user data into a list of maps
            List<Map<String, String>> users = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("id", String.valueOf(resultSet.getInt("id")));
                user.put("username", resultSet.getString("username"));
                user.put("email", resultSet.getString("email"));
                users.add(user);
            }

            // Close the CallableStatement (not the connection, as it's shared)
            stmt.close();

            // Convert the user data to JSON and send it as the response
            String json = convertUserListToJson(users);
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private String convertUserListToJson(List<Map<String, String>> users) {
        // Convert the list of user maps to a JSON string (you can use a JSON library)
        // Here's a simple example without a library:
        StringBuilder json = new StringBuilder("[");
        for (Map<String, String> user : users) {
            json.append("{");
            for (Map.Entry<String, String> entry : user.entrySet()) {
                json.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
            }
            json.deleteCharAt(json.length() - 1); // Remove the trailing comma
            json.append("},");
        }
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1); // Remove the trailing comma
        }
        json.append("]");
        return json.toString();
    }
}

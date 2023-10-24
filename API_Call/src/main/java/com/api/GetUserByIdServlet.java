package com.api;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUserByIdServlet")
public class GetUserByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int userId = Integer.parseInt(idParam);

            // Get a database connection using the centralized method
            Connection conn = JDBC_Connection.getConnection();

            // Create a CallableStatement for the stored procedure
            CallableStatement stmt = conn.prepareCall("{call get_user(?)}");
            stmt.setInt(1, userId);

            // Execute the stored procedure
            ResultSet resultSet = stmt.executeQuery();

            // Check if a user with the specified ID was found
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                // Send the user data as plain text response
                response.setContentType("text/plain");
                response.getWriter().write("User ID: " + id + "\n");
                response.getWriter().write("Username: " + username + "\n");
                response.getWriter().write("Email: " + email);
            } else {
                // Handle the case where no user with the specified ID was found
                response.setContentType("text/plain");
                response.getWriter().write("User not found for ID: " + userId);
            }

            // Close the CallableStatement (not the connection, as it's shared)
            stmt.close();
        } catch (NumberFormatException e) {
            // Handle the case where idParam is not a valid integer
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("Invalid ID. Please provide a valid numeric ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}

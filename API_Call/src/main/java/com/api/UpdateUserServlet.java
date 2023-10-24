package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");

        if (!isValidEmail(newEmail)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Invalid email format. Please provide a valid email address.");
            return;
        }

        try {
            // Get a database connection using the centralized method
            Connection conn = JDBC_Connection.getConnection();

            // Create a CallableStatement for the stored procedure
            CallableStatement stmt = conn.prepareCall("{call update_user(?,?,?)}");
            stmt.setInt(1, userId);
            stmt.setString(2, newUsername);
            stmt.setString(3, newEmail);

            // Execute the stored procedure
            stmt.executeUpdate();

            // Close the CallableStatement (not the connection, as it's shared)
            stmt.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User Updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User Not Updated Error: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        // Use a regular expression pattern for basic email validation
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailPattern, email);
    }
}

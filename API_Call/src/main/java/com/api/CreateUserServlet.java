package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (!isValidEmail(email)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Invalid email format. Please provide a valid email address.");
            return;
        }

        Connection conn = null;
        try {
            // Get a database connection using the centralized method
            conn = JDBC_Connection.getConnection();

            // Create a CallableStatement for the stored procedure
            CallableStatement stmt = conn.prepareCall("{call create_user(?, ?)}");
            stmt.setString(1, username);
            stmt.setString(2, email);

            // Execute the stored procedure
            stmt.execute();

            // Close the CallableStatement (not the connection, as it's shared)
            stmt.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User not created. Error: " + e.getMessage());
        } finally {
            // Close the connection in the finally block to ensure proper cleanup
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle or log the exception appropriately
                }
            }
        }
    }

    private boolean isValidEmail(String email) {
        // Use a regular expression pattern for basic email validation
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailPattern, email);
    }
}

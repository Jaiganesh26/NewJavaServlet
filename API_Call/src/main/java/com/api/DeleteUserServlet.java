package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int userId = Integer.parseInt(idParam);

            // Get a database connection using the centralized method
            Connection conn = JDBC_Connection.getConnection();

            // Create a CallableStatement for the stored procedure
            CallableStatement stmt = conn.prepareCall("call delete_user(?)");
            stmt.setInt(1, userId);

            // Execute the stored procedure
            stmt.executeUpdate();

            // Close the CallableStatement (not the connection, as it's shared)
            stmt.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User deleted successfully!");
        } catch (NumberFormatException e) {
            // Handle the case where the idParam is not a valid integer
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Invalid ID. Please provide a valid numeric ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("User not deleted. Error: " + e.getMessage());
        }
    }
}

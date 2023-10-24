package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Ajax";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1326";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String insertQuery = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword); // In a real application, hash the password

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected >=1) {
                response.getWriter().write("Registration successful!");
            } else {
                response.getWriter().write("Registration failed. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred. Please try again later.");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.domain;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String nickname = request.getParameter("nickname");
       

        // Validate email
        if (!isValidEmail(email)) {
            response.getWriter().println("Invalid email address.");
            return;
        }

        // Validate phone number
        if (!isValidPhoneNumber(contact)) {
            response.getWriter().println("Invalid phone number.");
            return;
        }

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String jdbcUsername = "root";
        String jdbcPassword = "1326";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
                // Insert user data into the database
                String sql = "INSERT INTO users2 (email, contact, nickname) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                	preparedStatement.setString(1, email);
                    preparedStatement.setString(2, contact);
                    preparedStatement.setString(3, nickname);
                    

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        response.getWriter().println("User registered successfully!");
                    } else {
                        response.getWriter().println("Failed to register user.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Validate email using a simple regular expression
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

    // Validate phone number using a simple regular expression
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{10}$";
        return Pattern.matches(phoneRegex, phoneNumber);
    }
}

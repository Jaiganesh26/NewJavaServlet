package com.model;

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

@WebServlet("/insertDetails")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/users";
    private static final String dbUser = "root";
    private static final String dbPassword = "1326";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        
            // Load the MySQL JDBC driver
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Create a database connection
            try (Connection connection = DriverManager.getConnection(jdbcURL,dbUser,dbPassword)) {
                String sql = "insert into users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                	pst.setString(1, username);
                	pst.setString(2, password);
                	pst.setString(3, email);

                    int rowsInserted = pst.executeUpdate();
                  //  System.out.println(rowsInserted);
                    if (rowsInserted > 0) {
                        response.sendRedirect("registration_success.jsp");
                    } else {
                        response.sendRedirect("registration_failed.jsp");
                    }
            
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}

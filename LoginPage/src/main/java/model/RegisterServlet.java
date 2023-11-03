package model;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCode = request.getParameter("zipCode");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dob");

        try {
            // Hash the password
            String hashedPassword = hashPassword(password);

            // Save user details to the database
            boolean success = registerUser(email, hashedPassword, firstName, lastName, address, city, state, zipCode, phoneNumber, dob);

            if (success) {
                // Redirect to the login page or send a success response
                response.sendRedirect("login.jsp");
            } else {
                // Registration failed, redirect to the registration page or send an error response
                response.sendRedirect("registration.jsp");
            }
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("registration.jsp");
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hashedPassword = new StringBuilder();

        for (byte b : hash) {
            hashedPassword.append(String.format("%02x", b));
        }

        return hashedPassword.toString();
    }

    private boolean registerUser(String email, String hashedPassword, String firstName, String lastName,
            String address, String city, String state, String zipCode, String phoneNumber, String dob)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud", "root", "1326");
        String query = "INSERT INTO decrypt (email, password, firstName, lastName, address, city, state, zipCode, phoneNumber, dob) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, hashedPassword);
        preparedStatement.setString(3, firstName);
        preparedStatement.setString(4, lastName);
        preparedStatement.setString(5, address);
        preparedStatement.setString(6, city);
        preparedStatement.setString(7, state);
        preparedStatement.setString(8, zipCode);
        preparedStatement.setString(9, phoneNumber);
        preparedStatement.setString(10, dob);

        int rowsAffected = preparedStatement.executeUpdate();
        conn.close();

        return rowsAffected > 0;
    }
}

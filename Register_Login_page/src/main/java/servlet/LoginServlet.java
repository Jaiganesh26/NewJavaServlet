package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Email validation using a simple regular expression
        if (!isValidEmail(email)) {
            response.sendRedirect("registration.jsp");
            return;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hashedPassword = new StringBuilder();

            for (byte b : hash) {
                hashedPassword.append(String.format("%02x", b));
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud", "root", "1326");
            String query = "SELECT email, password, firstName, lastName,address,city,state,zipCode,phoneNumber,dob FROM decrypt WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String storedPassword = result.getString("password");
                if (hashedPassword.toString().equals(storedPassword)) {
                    // Passwords match, user is authenticated
                    // Retrieve user details
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String address = result.getString("address");
                    String city = result.getString("city");
                    String state = result.getString("state");
                    String zipCode = result.getString("zipCode");
                    String phoneNumber = result.getString("phoneNumber");
                    String dob = result.getString("dob");

                    // Set user details as request attributes
                    request.setAttribute("email", email);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("address", address);
                    request.setAttribute("city", city);
                    request.setAttribute("state", state);
                    request.setAttribute("zipCode", zipCode);
                    request.setAttribute("phoneNumber", phoneNumber);
                    request.setAttribute("dob", dob);

                    // Forward to the user details JSP
                    request.getRequestDispatcher("userDetails.jsp").forward(request, response);
                } else {
                    // Passwords don't match, user authentication failed
                    response.sendRedirect("registration.jsp");
                }
            } else {
                // User not found in the database
                response.sendRedirect("registration.jsp");
            }

            conn.close();
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("registration.jsp");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }
}

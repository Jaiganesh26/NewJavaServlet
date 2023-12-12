package model;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long EXPIRATION_TIME =300; // 1 minute (in milliseconds)

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Hash the password
            String hashedPassword = hashPassword(password);

            // Validate user credentials and retrieve user details from the database
            User user = authenticateUser(email, hashedPassword);

            if (user != null) {
                // Generate JWT
                String token = generateJWT(user.getEmail(), user.getId());

                // Set the JWT as an attribute in the request
                request.setAttribute("jwtToken", token);

                // Set user details as attributes in the request
                request.setAttribute("email", user.getEmail());
                request.setAttribute("firstName", user.getFirstName());
                request.setAttribute("lastName", user.getLastName());
                request.setAttribute("address", user.getAddress());
                request.setAttribute("city", user.getCity());
                request.setAttribute("state", user.getState());
                request.setAttribute("zipCode", user.getZipCode());
                request.setAttribute("phoneNumber", user.getPhoneNumber());
                request.setAttribute("dob", user.getDob());
                request.setAttribute("imageFileName",user.getImageFileName());
                

                // Forward to the user details JSP
                if(token!=null) {
                request.getRequestDispatcher("userDetails.jsp").forward(request, response);
                }else
                    response.sendRedirect("registration.jsp");

           } else {
               // Redirect to the login JSP page with an error message
               request.setAttribute("errorMessage", "User not found. Please check your credentials.");
               request.getRequestDispatcher("login.jsp").forward(request, response);
           }

        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Redirect to the login JSP page (or any error page)
            response.sendRedirect("login.jsp");
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

    private User authenticateUser(String email, String hashedPassword)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud", "root", "1326");
        String query = "SELECT id, firstName, lastName, email, address, city, state, zipCode, phoneNumber, dob, imageFileName FROM decrypt WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, hashedPassword);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            int id = result.getInt("id");
            String email1 = result.getString("email");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String address = result.getString("address");
            String city = result.getString("city");
            String state = result.getString("state");
            String zipCode = result.getString("zipCode");
            String phoneNumber = result.getString("phoneNumber");
            String dob = result.getString("dob");
            String imageFileName=result.getString("imageFileName");

            return new User(id, email1, firstName, lastName, address, city, state, zipCode, phoneNumber, dob,imageFileName);
        }
        return null;
    }

    private String generateJWT(String email, int userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim("email", email)
                .claim("sub", Integer.toString(userId))
                .signWith(SignatureAlgorithm.HS256, "1234")
                .compact();
    }
}
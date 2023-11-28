package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        // Password validation
        if (!isValidPassword(password)) {
            // Password is not valid, redirect to the registration page with an error message
            response.sendRedirect("registration.jsp?error=password");
            return;
        }

        // Date validation
        if (!isValidDate(dob)) {
            // Date is not valid, redirect to the registration page with an error message
            response.sendRedirect("registration.jsp?error=date");
            return;
        }

        System.out.println("In do post method of Add Image servlet.");
        Part file = request.getPart("image");

        String imageFileName = file.getSubmittedFileName(); // get selected image file name
        System.out.println("Selected Image File Name : " + imageFileName);

        String uploadPath = "C:/Users/ganes/eclipse-workspace/LoginPage/src/main/webapp/image/" + imageFileName; // upload path where we have to upload our actual image
        System.out.println("Upload Path : " + uploadPath);

        // Uploading our selected image into the images folder
        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Hash the password
            String hashedPassword = hashPassword(password);

            // Save user details to the database
            boolean success = registerUser(email, hashedPassword, firstName, lastName, address, city, state, zipCode,
                    phoneNumber, dob, imageFileName);

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

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*");
    }

    private boolean isValidDate(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dob);
            // Ensure the parsed date matches the input date
            return sdf.format(date).equals(dob);
        } catch (ParseException e) {
            // Date parsing failed
            return false;
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

    private boolean registerUser(String email, String hashedPassword, String firstName, String lastName, String address,
            String city, String state, String zipCode, String phoneNumber, String dob, String imageFileName)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Crud", "root", "1326");
        String query = "INSERT INTO decrypt (email, password, firstName, lastName, address, city, state, zipCode, phoneNumber, dob, imageFileName) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        preparedStatement.setString(11, imageFileName);

        int rowsAffected = preparedStatement.executeUpdate();
        conn.close();
        return rowsAffected > 0;
    }
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/insertData")
public class InsertDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Regular expression pattern for a valid email address
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Get a writer pointer to display the successful result
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Initialize the database
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","1326");

            // Get data from the request
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String designation = request.getParameter("designation");
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");

            // Validate email address using the isValidEmail method
            if (!isValidEmail(email)) {
                out.println("<html><body><b>Invalid Email Address</b></body></html>");
                return;
            }

            // Create a SQL query to insert data into the demo table
            PreparedStatement st = con.prepareStatement("INSERT INTO demo (id,name,designation,dob,email,contact)VALUES (?, ?, ?, ?, ?, ?)");

            // Set the parameters for the query
            st.setInt(1, id);
            st.setString(2, name);
            st.setString(3, designation);
            st.setString(4, dob);
            st.setString(5, email);
            st.setString(6, contact);

            // Execute the insert command using executeUpdate() to make changes in the database
            st.executeUpdate();

            out.println("<font color='green'>  Record Addedddd   </font>");
         // Close all the connections
            st.close();
            con.close();
            
        } catch (Exception e) {
        	out.println("<font color='red'>  Record Failed   </font>");
        	e.printStackTrace();
        }
    }

    // Method to validate an email address
   private boolean isValidEmail(String email) {
        // Compile the email regex pattern
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        // Match the given email against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
}

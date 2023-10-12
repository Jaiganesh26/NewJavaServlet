package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/companyDataServlet")
public class companyDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String selectedCompany = request.getParameter("company");
        //System.out.println(selectedCompany);

        try {
            // JDBC URL, username, and password of MySQL server
            String jdbcUrl = "jdbc:mysql://localhost:3306/company";
            String dbUser = "root";
            String dbPassword = "1326";

            // Load the JDBC driver (use "com.mysql.cj.jdbc.Driver" for MySQL 8)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL query to retrieve company data
            String sql = "SELECT location,email FROM companies WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
           // System.out.println(stmt);
            stmt.setString(1, selectedCompany);
            ResultSet rs = stmt.executeQuery();

            Map<String, Object> companyData = new HashMap<>();
            Map<String, String> locations = new HashMap<>();
            Map<String, String> emails = new HashMap<>();

            while (rs.next()) {
            	System.out.println(rs.getString("location"));
                String location = rs.getString("location");
                String email = rs.getString("email");
                
                // Store location and email in their respective maps
                locations.put("location", location);
                emails.put("email", email);
            }

            // Close the ResultSet, PreparedStatement, and Connection
            rs.close();
            stmt.close();
            conn.close();

            // Store locations and emails maps in companyData
            companyData.put("locations", locations);
            companyData.put("emails", emails);
            
           // System.out.println(locations);
           // System.out.println(emails);

            // Convert the data to JSON
            String jsonData = new Gson().toJson(companyData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

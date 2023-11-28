package com.image;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("In do post method of Display Image servlet.");
        String imageId = request.getParameter("imageId");
        int id = Integer.parseInt(imageId);

        // getting database connection (jdbc code)
        Connection connection = null;
        int imgId = 0;
        String imgFileName = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "1326");
            String query = "select * from image where id=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    imgId = rs.getInt("id");
                    imgFileName = rs.getString("image_data");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        RequestDispatcher rd;
        if (imgFileName != null) {
            request.setAttribute("id", String.valueOf(imgId));
            request.setAttribute("img", imgFileName);
            rd = request.getRequestDispatcher("displayImage.jsp");
        } else {
            request.setAttribute("error", "Image not found for the given ID.");
            rd = request.getRequestDispatcher("error.jsp");
        }
        rd.forward(request, response);
    }
}

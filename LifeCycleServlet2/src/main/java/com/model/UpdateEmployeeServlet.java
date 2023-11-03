package com.model;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    Connection con;
    PreparedStatement pst;

    public void doPut(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String id = req.getParameter("id");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lba", "root", "1326");

            pst = con.prepareStatement("UPDATE employee SET fname = ?, lname = ? WHERE id = ?");
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, id);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("<font color='green'>Employee Updated Successfully</font>");
            } else {
                out.println("<font color='red'>Employee Not Found</font>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            out.println("<font color='red'>Error: " + ex.getMessage() + "</font>");
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                out.println("<font color='red'>Error: " + ex.getMessage() + "</font>");
            }
        }
    }
}

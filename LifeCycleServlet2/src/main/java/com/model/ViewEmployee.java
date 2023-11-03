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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/viewemployee")
public class ViewEmployee extends HttpServlet {
    Connection con;
    ResultSet rs;

    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lba", "root", "1326");
            String sql;

            sql = "select * from employee";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> EmpID </td>");
            out.println("<td> Firstname </td>");
            out.println("<td> Lastname </td>");
            out.println("<td> Edit </td>");
            out.println("<td> Delete </td>");

            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td>");
                out.println("<td>" + rs.getString("fname") + "</td>");
                out.println("<td>" + rs.getString("lname") + "</td>");

                out.println("<td>" + "<a href='updateEmployee?id=" + rs.getString("id") + "'> Edit </a>" + "</td>");
                out.println("<td>" + "<a href='Delete?id=" + rs.getString("id") + "'> Delete </a>" + "</td>");
                out.println("</tr>");
            }

            out.println("</table");
        } catch (ClassNotFoundException | SQLException ex) {
            out.println(ex.getMessage());
            out.println("<font color='red'>  Record Failed   </font>");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
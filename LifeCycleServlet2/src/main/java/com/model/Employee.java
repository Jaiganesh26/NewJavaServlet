package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employee")

public class Employee extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    
    public void doPost(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        
        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lba","root","1326");
            String id = req.getParameter("id");
            String fname = req.getParameter("fname");
            String lname= req.getParameter("lname");
            
            pst = con.prepareStatement("insert into employee(id,fname,lname)values(?,?,?) ");
            pst.setString(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            row = pst.executeUpdate();
            
            out.println("<font color='green'>  Record Addedddd   </font>");
 
            
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
        } 
    }
  
}
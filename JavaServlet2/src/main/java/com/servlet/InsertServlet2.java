/*package com.servlet;

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

@WebServlet("/insertServlet")

public class InsertServlet2 extends HttpServlet {
    
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","1326");
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String designation= req.getParameter("designation");
            String dob = req.getParameter("dob");
            String email= req.getParameter("email");
            String contact= req.getParameter("contact");
            
            pst = con.prepareStatement("insert into demo(id,name,designation,dob,email,contact)values(?,?,?,?,?,?) ");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, designation);
            pst.setString(4, dob);
            pst.setString(5, email);
            pst.setString(6, contact);
            row = pst.executeUpdate();
            
            out.println("<font color='green'>  Record Addedddd   </font>");
 
            
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
        } 
    }
  
}*/
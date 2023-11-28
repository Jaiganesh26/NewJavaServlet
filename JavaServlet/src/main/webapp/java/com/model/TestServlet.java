package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/getDetails")
public class TestServlet extends HttpServlet {
	 
	public void service(ServletRequest req,ServletResponse res) throws IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","1326");
			Statement stmt=con.createStatement();
			String sql="Select * from employee";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				out.print(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+"<br>");
				
			}
			stmt.close();
			con.close();
			}catch(Exception e) {
				out.println(e.toString());
			}
	}
}
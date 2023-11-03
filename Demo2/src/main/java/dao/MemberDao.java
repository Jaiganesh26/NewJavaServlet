package dao;

import java.util.*;

import java.sql.*;

import entity.Member;

public class MemberDao {
	// Data Access Object for the Member Class
	public static Connection getConnection() {
		// Creates connection to the database, will be called from other functions
		
		Connection connection = null;
		
		try {
			// Establishing the connection via JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "1326");
			System.out.println("Connected...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		return connection;
	}
	
	public static int save(Member m) {
		// Inserts Data into the table
		
		int status = 0;
		Connection con = MemberDao.getConnection(); // Create Connection
		try {
			// Insert Operation
			PreparedStatement ps = con.prepareStatement("INSERT INTO Member VALUES(?, ?, ?, ?)");
			
			ps.setInt(1, m.getId());
			ps.setString(2, m.getName());
			ps.setString(3, m.getEmail());
			ps.setInt(4, m.getAge());
			
			status = ps.executeUpdate(); // Execute the Query 
			
			con.close(); // Close the connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int update(Member m) {
		// Updates data in the table
		
		int status = 0;
		Connection con = MemberDao.getConnection(); // Create Connection
		try {
			// Insert Operation
			PreparedStatement ps = con.prepareStatement("UPDATE Member SET id=?, name=?, email=?, age=? WHERE id=?");
			
			ps.setInt(1, m.getId());
			ps.setString(2, m.getName());
			ps.setString(3, m.getEmail());
			ps.setInt(4, m.getAge());
			ps.setInt(5, m.getId());
			
			status = ps.executeUpdate(); // Execute the Query & assign status
			
			con.close(); // Close the connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int delete(int id) {
		// Deletes Data from the table using Id
		
		int status = 0;
		Connection con = MemberDao.getConnection();
		try {
			// Delete Operation
			PreparedStatement ps = con.prepareStatement("DELETE FROM Member WHERE id=?");
			
			ps.setInt(1, id);
			
			status = ps.executeUpdate(); // Execute the Query & assign status
			
			con.close(); // Close the connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static Member getMemberById(int id) {
		// Select particular row in Database using id and returns a Member object containing data of the query
		
		Member m = new Member(); // Creating new Member object to store query data which will be returned when this function is called
		
		Connection con = MemberDao.getConnection();
		try {
			// Select by Id Operation
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Member WHERE id=?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // Executing Query & Storing result in 'rs'
			
			if(rs.next()) {
				// Checks if there is data in the 'rs' and stores it in the Member object 'm' created earlier
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setEmail(rs.getString(3));
				m.setAge(rs.getInt(4));
			}
			
			con.close(); // Close the connection
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}

	    public static List<Member> getAllMembers() {
	        List<Member> list = new ArrayList<>();
	        Connection con = MemberDao.getConnection();
	        try {
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM Member ORDER BY id");
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Member member = new Member();
	                member.setId(rs.getInt("id"));
	                member.setName(rs.getString("name"));
	                member.setEmail(rs.getString("email"));
	                member.setAge(rs.getInt("age"));
	                list.add(member);
	            }
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	}



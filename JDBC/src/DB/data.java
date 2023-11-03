package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class data {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 String Company="tcs";
	        
         String jdbcUrl = "jdbc:mysql://localhost:3306/company";
         String dbUser = "root";
         String dbPassword = "1326";

         Class.forName("com.mysql.cj.jdbc.Driver");

         Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

         String sql = "SELECT location, email FROM companies WHERE name = ?";
         PreparedStatement stmt = conn.prepareStatement(sql);
         stmt.setString(1,Company);
         ResultSet rs = stmt.executeQuery();
         
         String location = null;
         String email=null;
         
         Map<String, Object> companyData = new HashMap<>();
         Map<String, String> locations = new HashMap<>();
         Map<String, String> emails = new HashMap<>();

         while (rs.next()) {
             location = rs.getString("location");
             email = rs.getString("email");
             
             locations.put("location", location);
             emails.put("email", email);
         }

         rs.close();
         stmt.close();
         conn.close();

         companyData.put("locations", locations);
         companyData.put("emails", emails);

     System.out.println(locations);
     System.out.println(emails);

	}

}

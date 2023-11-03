package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GenerateTokenServlet")
public class GenerateTokenServlet extends HttpServlet {
    private static final long EXPIRATION_TIME = 300; // 30 seconds (in milliseconds)
    private static final String SECRET_KEY = "yourSecretKey"; // Replace with your secret key

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jwtToken = generateJwtToken();

        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Display the generated token on the JSP page
        request.setAttribute("token", jwtToken);
        try {
            getServletContext().getRequestDispatcher("/generateToken.jsp").include(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private String generateJwtToken() {
        // Set the expiration time for the token (e.g., 30 seconds from now)
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        // Generate the JWT token
        return Jwts.builder()
                .setSubject("jai") // The subject of the token
                .setExpiration(expirationDate) // Expiration time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign the token with the secret key
                .compact();
    }
}

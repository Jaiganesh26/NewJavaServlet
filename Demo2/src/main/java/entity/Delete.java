package entity;

import java.io.IOException;

import dao.MemberDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Deletes the member record
		
		int id = Integer.parseInt(request.getParameter("id")); // getting the id parameter
		
		MemberDao.delete(id); // Executing the delete operation
		
		request.getRequestDispatcher("View").forward(request, response); // Redirects back to view all member page
	}

}

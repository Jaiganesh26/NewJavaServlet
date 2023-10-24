package entity;

import java.io.IOException;
import java.util.List;

import dao.MemberDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public View() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Displays All Members
		
		response.setContentType("text/html");
		
		// Getting all Members in an Array List
		List<Member> list = MemberDao.getAllMembers();
		System.out.println("List size: " + list.size());
		request.setAttribute("list", list); // Sending the ArrayList with the name "list" to be used in the jsp/jstl
		request.getRequestDispatcher("view.jsp").forward(request, response); // render view.jsp
		
	}

}

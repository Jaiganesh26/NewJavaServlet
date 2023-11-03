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
        // Retrieve the list of members using the MemberDao
        List<Member> members = MemberDao.getAllMembers();

        // Set the list of members as an attribute in the request
        request.setAttribute("list", members);

        // Forward the request to the JSP page
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

}

package com.employee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.EmployeeDao;
import com.employee.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private EmployeeDao employeeDao =new EmployeeDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/employeeregister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String address = request.getParameter("address");
	    String contact = request.getParameter("contact");
	    
	    Employee employee = new Employee();
	    employee.setFirstName(firstName);
	    employee.setLastName(lastName);
	    employee.setUsername(username);
	    employee.setPassword(password);
	    employee.setAddress(address);
	    employee.setContact(contact);

	    try {
	        // Assuming employeeDao.registerEmployee returns a success indicator
	        boolean success = employeeDao.registerEmployee(employee);

	        if (success) {
	            // If registration was successful, forward to employeedetails.jsp
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/employeedetails.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            // Handle registration failure, perhaps by displaying an error message
	            // You can forward to an error page or handle it in some other way
	            // For now, we'll redirect back to the registration page with an error parameter
	            response.sendRedirect(request.getContextPath() + "/employeedetails.jsp");
	        }
	    } catch (ClassNotFoundException e) {
	        // Handle the exception properly (e.g., log it) and provide user feedback
	        e.printStackTrace();
	        response.sendRedirect(request.getContextPath() + "/employeeregister.jsp?error=registration_error");
	    }
	}


}

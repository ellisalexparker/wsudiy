package wsudiy;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signupview")

public class Sign_Up_Servlet extends HttpServlet{
	
    private static final long serialVersionUID = 1L;
    private Application_DAO application_DAO;
 
    public void initialize() {
        application_DAO = new Application_DAO(); 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	initialize();
    	
    	String first_name = request.getParameter("fname");
    	String last_name = request.getParameter("lname");
    	String birthday = request.getParameter("birthday");
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password_repeat = request.getParameter("password-repeat");
        
        Registered_Users user = new Registered_Users();
        
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setBirthday(birthday);
        user.setUsername(email);
        user.setPassword(password);
        
        try {
        	
        	boolean validEmail = application_DAO.checkEmail(email);
			 
			if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty()) {
				response.sendRedirect("Error.jsp");
	        }
	        
			else if(password != password_repeat) {
				response.sendRedirect("Error.jsp");
	        }
	        
			else if(validEmail == false) {
				response.sendRedirect("Error.jsp");
			}
			
			else if(validEmail == true) {
				application_DAO.registerUser(user);
			}
	        
	        else {
	        	response.sendRedirect("Error.jsp");
	        }
			
		} 
        
        catch (SQLException e) {
			e.printStackTrace();
		}
        
        response.sendRedirect("MainMenu.jsp");
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
    }
}
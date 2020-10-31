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

@WebServlet("/loginview")

public class Login_Servlet extends HttpServlet{
	
    private static final long serialVersionUID = 1L;
    private Application_DAO application_DAO;
 
    public void initialize() {
        application_DAO = new Application_DAO(); 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	initialize();
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        //call login check
        
        try {
        	
        	boolean validUser = application_DAO.checkCredentials(username, password);
        	
        	if(username.isEmpty() || password.isEmpty()) {
            	response.sendRedirect("Error.jsp");
            }
        	
        	else if((username.equalsIgnoreCase("root") && password.equals("pass1234"))) {
                response.sendRedirect("Root_User_Landing_Page.jsp");
            }
        	
            else if(validUser == true) {
            	response.sendRedirect("Registered_User_Landing_Page.jsp");
            }
        	
            else if(validUser == false) {
            	response.sendRedirect("Error.jsp");
            }
        	
            else
                response.sendRedirect("Error.jsp");
        }
        
        catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
    }
}
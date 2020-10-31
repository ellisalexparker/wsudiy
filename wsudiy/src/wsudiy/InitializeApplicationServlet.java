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

@WebServlet("/initializeview")

public class InitializeApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Application_DAO application_DAO;
	
	public void initialize() {
		application_DAO = new Application_DAO(); 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		initialize();
		//when the initialize database button is pressed, we will drop all existing databases and fill database with data
		
		String action = request.getServletPath();
		
		if(action == "/initialize") {
			try {
				application_DAO.resetDatabase();
				application_DAO.fillDatabase();
				response.sendRedirect("Success.jsp");
			}
			
		    catch (SQLException e) {
				e.printStackTrace();
			}
		    	response.sendRedirect("Root_User_Landing_Page.jsp");
		    }
		}
}
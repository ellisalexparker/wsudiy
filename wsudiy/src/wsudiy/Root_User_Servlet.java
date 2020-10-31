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

@WebServlet("/rootview")

public class Root_User_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Application_DAO application_DAO;
 
    public void initialize() {
        application_DAO = new Application_DAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/insert":
            	insertVideo(request, response);
                break;
            case "/delete":
            	deleteVideo(request, response);
                break;
            //case "/edit":
               // showEditForm(request, response);
                //break;
            case "/update":
            	updateVideo(request, response);
                break;
            case "/list":
            	listVideos(request, response);
            case "/initialize":
            	initializeApplication(request, response);
            default:          	
            	listVideos(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listVideos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Videos> videos_list = Application_DAO.listAllVideos();
        request.setAttribute("videos_list", videos_list);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("VideoCatalog.jsp");       
        dispatcher.forward(request, response);
    }
    
    private void initializeApplication(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("InitializeApplication.jsp");
    	dispatcher.forward(request, response);
    }
    
 
    private void showInsertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertVideosForm.jsp");
        dispatcher.forward(request, response);
    }
/*
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String url = request.getParameter("url");
        Videos existing_video = Application_DAO.getVideos(url);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("video", existing_video);
        dispatcher.forward(request, response);
    }
 */
    @SuppressWarnings("unused")
	private void insertVideo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	String url = request.getParameter("url");
    	String title = request.getParameter("title");
    	String description = request.getParameter("description");
    	String date = request.getParameter("date");
    	String diy_question = request.getParameter("diy_question");
    	String author = request.getParameter("author");
    	
    	Videos new_video = new Videos(url, title, description, date, diy_question, author);
    	Application_DAO.insert(new_video);		
    	
        response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }
 
    private void updateVideo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	String url = request.getParameter("url");
    	System.out.println(url);
    	
    	String title = request.getParameter("title");
    	String description = request.getParameter("description");
    	String date = request.getParameter("date");
    	String diy_question = request.getParameter("diy_question");
    	String author = request.getParameter("author");
    	
    	System.out.println(author);
    	
    	Videos video = new Videos(url, title, description, date, diy_question, author);
    	Application_DAO.update(video);
    	
    	response.sendRedirect("list");
    	
    }
 
    private void deleteVideo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String url = request.getParameter("url");
        Application_DAO.delete(url);
        response.sendRedirect("list");
    }
}
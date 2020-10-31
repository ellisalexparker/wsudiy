package wsudiy;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;

public class Application_DAO {  
	
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public Application_DAO() {

    }
	
    protected static void connect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wsudiy?"
  			          + "useSSL=false"
  			          + "&serverTimezone=UTC"
  			          + "&allowPublicKeyRetrieval=true"
  			          + "&user=root"
  			          + "&password=ellisparker");
            
            System.out.println(connect);
        }
    }
    
    protected static void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public boolean resetDatabase() throws SQLException{
    	connect();
    	
    	String sql1 = "DROP DATABASE wsudiy;";
    	String sql2 = "CREATE DATABASE wsudiy;";
    	String sql3 = "USE wsudiy;";
  
    	preparedStatement = connect.prepareStatement(sql1);
    	preparedStatement.executeUpdate(sql1);
    	
    	preparedStatement = connect.prepareStatement(sql2);
    	preparedStatement.executeUpdate(sql2);
    	
    	preparedStatement = connect.prepareStatement(sql3);
    	preparedStatement.executeUpdate(sql3);
    	
    	boolean databaseReset = preparedStatement.executeUpdate() > 0;
    	
    	preparedStatement.close();
    	
    	disconnect();
    	
    	return databaseReset;
    }
    
    public boolean fillDatabase() throws SQLException {
    	connect();
    	
    	String sql1 = 
    			"CREATE TABLE registered_user (\r\n"
    			+ "	first_name VARCHAR (50), \r\n"
    			+ "	last_name VARCHAR (50), \r\n"
    			+ "	birthday VARCHAR(100), \r\n"
    			+ "	gender VARCHAR (50), \r\n"
    			+ "	username VARCHAR(50), \r\n"
    			+ "	passwrd VARCHAR (50),\r\n"
    			+ "	PRIMARY KEY (username));\r\n"
    			+ "\r\n"
    			+ "CREATE TABLE videos (\r\n"
    			+ "	title VARCHAR (50), \r\n"
    			+ "	descrption VARCHAR (120), \r\n"
    			+ "	url VARCHAR (50), \r\n"
    			+ "	upload_date VARCHAR(100), \r\n"
    			+ "	diy_question VARCHAR (100), \r\n"
    			+ "	author VARCHAR(50),\r\n"
    			+ "	PRIMARY KEY (url),\r\n"
    			+ "	FOREIGN KEY (author) REFERENCES registered_user(username));\r\n"
    			+ "\r\n"
    			+ "CREATE TABLE diy_questions (\r\n"
    			+ "	question VARCHAR (100), \r\n"
    			+ "	question_id INTEGER, \r\n"
    			+ "	upload_date VARCHAR(100), \r\n"
    			+ "	tags VARCHAR(1000), \r\n"
    			+ "	PRIMARY KEY (question_id));\r\n"
    			+ "\r\n"
    			+ "CREATE TABLE reviews (\r\n"
    			+ "	url VARCHAR (50), \r\n"
    			+ "	score VARCHAR (10), \r\n"
    			+ "	author VARCHAR (50),\r\n"
    			+ "	review_id VARCHAR (50), \r\n"
    			+ "	PRIMARY KEY (review_id), \r\n"
    			+ "	FOREIGN KEY (url) REFERENCES videos(url), \r\n"
    			+ "	FOREIGN KEY (author) REFERENCES registered_user(username));\r\n"
    			+ "\r\n"
    			+ "CREATE TABLE favorites (\r\n"
    			+ "	username VARCHAR (50), \r\n"
    			+ "	video_name VARCHAR (50), \r\n"
    			+ "	video_link VARCHAR (50), \r\n"
    			+ "	upload_date VARCHAR(100),\r\n"
    			+ "	diy_question INTEGER, \r\n"
    			+ "	list_id INTEGER,\r\n"
    			+ "	PRIMARY KEY (list_id),\r\n"
    			+ "	FOREIGN KEY (video_link) REFERENCES videos(url),\r\n"
    			+ "	FOREIGN KEY (diy_question) REFERENCES diy_questions(question_id), \r\n"
    			+ "	FOREIGN KEY (username) REFERENCES registered_user(username));";
    	
    	String sql2 = 
    			"INSERT INTO diy_questions VALUES (\"how to tie a tie\", 1, \"Monday, January 5, 1976\", \"clothing\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to remodel a bathroom\", 2, \"Monday, December 1, 1980\", \"home improvement\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to roast cacao nibs\", 3, \"Wednesday, June 2, 1982\", \"food\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to check your credit score\", 4, \"Friday, February 26, 1988\", \"finance\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to solve sodoku puzzles\", 5, \"Sunday, September 24, 1989\", \"games\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to cook a steak\", 6, \"Monday, June 28, 1993\", \"food\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to sign the alphabet in asl\", 7, \"Tuesday, August 30, 1994\", \"lifestyle\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to put up a shelf\", 8, \"Friday, August 2, 1996\", \"home improvement\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to support your local food bank\", 9, \"Sunday, July 30, 2000\", \"community service\");\r\n"
    			+ "INSERT INTO diy_questions VALUES (\"how to make cold brew at home\", 10, \"Saturday, August 23, 2008\", \"food\");"
    			+ " ";
    	
    	String sql3 =
    			"INSERT INTO registered_user VALUES (\"Roland\", \"Hill\", \"Sunday, May 26, 2002\", \"male\", \"rhill\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Denzel\", \"Sinclair\", \"Monday, March 1, 2004\", \"male\", \"dsinclair\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Abdulrahman\", \"Kelley\", \"Wednesday, September 1, 2004\", \"nonbinary\", \"akelley\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Braydon\", \"Legge\", \"Saturday, March 8, 2008\", \"male\", \"blegge\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Kieren\", \"Reed\", \"Saturday, July 4, 2009\", \"female\", \"kreed\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Octavia\", \"Meyer\", \"Saturday, October 31, 2009\", \"female\", \"omeyer\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Emilee\", \"Dickinson\", \"Friday, August 24, 2018\", \"female\", \"edickinson\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Tahmid\", \"Rankin\", \"Monday, January 13, 2020\", \"nonbinary\", \"trankin\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Isla\", \"Mccormack\", \"Tuesday, April 28, 2020\", \"nonbinary\", \"imccormack\", \"password\");\r\n"
    			+ "INSERT INTO registered_user VALUES (\"Gloria\", \"Stark\", \"Thursday, August 27, 2020\", \"female\", \"gstark\", \"password\");"
    			+ " ";
    	
    	String sql4 =
    			"INSERT INTO videos VALUES (\"my first tie\", \"tying a tie for the first time!\", \"url1\", \"Tuesday, November 7, 2000\", 1, \"dsinclair\");\r\n"
    			+ "INSERT INTO videos VALUES (\"how to tie the best tie in the world\", \"my dad taught me how to tie a tie this way...\", \"url2\", \"Monday, December 22, 2003\", 1, \"rhill\");\r\n"
    			+ "INSERT INTO videos VALUES (\"putting a new shelf in my bathroom\", \"this is me attempting to put a shelf in my bathroom\", \"url3\", \"Thursday, March 10, 2005\", 8, \"omeyer\");\r\n"
    			+ "INSERT INTO videos VALUES (\"the perfect cold brew\", \"in this video i show you how to make the perfect cold brew\", \"url4\", \"Sunday, July 27, 2008\", 10, \"gstark\");\r\n"
    			+ "INSERT INTO videos VALUES (\"how to solve soduku puzzles in 10 minutes\", \"my tips and tricks for solving sudoku puzzles in 10 minutes or less\", \"url5\", \"Saturday, September 20, 2008\", 5, \"akelley\");\r\n"
    			+ "INSERT INTO videos VALUES (\"where to volunteer in souther california\", \"explore volunteer opportunities in socal\", \"url6\", \"Sunday, June 23, 2013\", 9, \"akelley\");\r\n"
    			+ "INSERT INTO videos VALUES (\"learn how to sign in 15 minutes!\", \"learn how to sign the alphabet in asl in 15 minutes\", \"url7\", \"Wednesday, April 8, 2015\", 7, \"omeyer\");\r\n"
    			+ "INSERT INTO videos VALUES (\"gordon ramsey steak taste test\", \"here is me attempting to make and taste steak from gordon ramsey's kitchen\", \"url8\", \"Tuesday, May 15, 2018\", 6, \"edickinson\");\r\n"
    			+ "INSERT INTO videos VALUES (\"my dog knows the alphabet\", \"totally not clickbait\", \"url9\", \"Wednesday, September 5, 2018\", 4, \"trankin\");\r\n"
    			+ "INSERT INTO videos VALUES (\"roasted cacao nibs and yogurt\", \"trying this recipe for the first time\", \"url10\", \"Saturday, January 19, 2019\", 3, \"blegge\");"
    			+ " ";
    	
    	String sql5 =
    			"INSERT INTO reviews VALUES (\"url8\", \"2 stars\", \"dsinclair\", 1);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url3\", \"5 stars\", \"edickinson\", 2);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url4\", \"3 stars\", \"rhill\", 3);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url4\", \"1 star\", \"gstark\", 4);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url1\", \"4 stars\", \"gstark\", 5);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url2\", \"5 stars\", \"kreed\", 6);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url10\", \"2 stars\", \"trankin\", 7);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url2\", \"3 stars\", \"trankin\", 8);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url8\", \"5 stars\", \"edickinson\", 9);\r\n"
    			+ "INSERT INTO reviews VALUES (\"url6\", \"4 stars\", \"kreed\", 10);"
    			+ " ";
    	
    	String sql6 = 
    			"INSERT INTO favorites VALUES (\"kreed\", \"my first tie\", \"url1\", \"Tuesday, November 7, 2000\", 1, 1);\r\n"
    			+ "INSERT INTO favorites VALUES (\"edickinson\", \"the perfect cold brew\", \"url4\", \"Sunday, July 27, 2008\", 10, 2);\r\n"
    			+ "INSERT INTO favorites VALUES (\"kreed\", \"how to tie the best tie in the world\", \"url2\", \"Monday, December 22, 2003\", 1, 1);\r\n"
    			+ "INSERT INTO favorites VALUES (\"trankin\", \"putting a new shelf in my bathroom\", \"url3\", \"Thursday, March 10, 2005\", 8, 3);\r\n"
    			+ "INSERT INTO favorites VALUES (\"kreed\", \"how to solve sodoku puzzles in 10 minutes\", \"url5\", \"Saturday, September 20, 2008\", 5, 1);\r\n"
    			+ "INSERT INTO favorites VALUES (\"trankin\", \"roasted cacao nibs and yogurt\", \"url10\", \"Saturday, January 19, 2019\", 3, 4);\r\n"
    			+ "INSERT INTO favorites VALUES (\"gstark\", \"my first tie\", \"url1\", \"Tuesday, November 7, 2000\", 1, 5);\r\n"
    			+ "INSERT INTO favorites VALUES (\"edickinson\", \"gordon ramsey steak taste test\", \"url8\", \"Tuesday, May 15, 2018\", 6, 2);\r\n"
    			+ "INSERT INTO favorites VALUES (\"gstark\", \"how to tie the best tie in the world\", \"url2\", \"Monday, December 22, 2003\", 1, 5);\r\n"
    			+ "INSERT INTO favorites VALUES (\"kreed\", \"learn how to sign in 15 minutes\", \"url7\", \"Wednesday, April 8, 2015\", 7, 1);"
    			+ " ";
    	
    	preparedStatement = connect.prepareStatement(sql1);
    	preparedStatement.execute(sql1);
    	
    	preparedStatement = connect.prepareStatement(sql2);
    	preparedStatement.execute(sql2);
    	
    	preparedStatement = connect.prepareStatement(sql3);
    	preparedStatement.execute(sql3);
    	
    	preparedStatement = connect.prepareStatement(sql4);
    	preparedStatement.execute(sql4);
    	
    	preparedStatement = connect.prepareStatement(sql5);
    	preparedStatement.execute(sql5);
    	
    	preparedStatement = connect.prepareStatement(sql6);
    	preparedStatement.execute(sql6);
    	
    	boolean rowInserted = preparedStatement.executeUpdate() > 0;
    	
    	preparedStatement.close();
    	
    	disconnect();
    	
    	return rowInserted;
    }
    
    
    
    public boolean checkEmail(String email) throws SQLException {
    	connect();
    	
    	String sql = "SELECT COUNT(*) FROM registered_user WHERE email = '" + email + "'";
    	
    	boolean validEmail = true;
    	
    	preparedStatement = connect.prepareStatement(sql);
    	
    	resultSet = preparedStatement.executeQuery();
    	
    	while(resultSet.next()) {
    		validEmail = false;
    	}
    	
    	preparedStatement.close();
    	
    	disconnect();
    	
    	return validEmail;
    	
    }
    
    public boolean checkCredentials(String email, String password) throws SQLException {
    	connect();
    	
    	String sql = "SELECT * FROM registered_user WHERE username = ? and passwrd = ?";
    	
    	boolean validCredentials = false;
    	
    	preparedStatement = connect.prepareStatement(sql);
    	
    	preparedStatement.setString(1, email);
    	preparedStatement.setString(2, password);
    	
    	resultSet = preparedStatement.executeQuery();
    	
    	while(resultSet.next()) {
    		validCredentials = true;
    	}
    	
    	preparedStatement.close();
    	
    	disconnect();
    	
    	return validCredentials;
    }
    
    public boolean registerUser(Registered_Users user) throws SQLException {
    	connect();
    	
    	String sql = "INSERT INTO registered_user(first_name, last_name, birthday, gender, username, passwrd) VALUES (?, ?, ?, ?, ?, ?)";
    	
    	boolean userRegistered;
    	
    	preparedStatement = connect.prepareStatement(sql);
    	
    	preparedStatement.setString(1, user.first_name);
    	preparedStatement.setString(2, user.last_name);
    	preparedStatement.setString(3, user.birthday);
    	preparedStatement.setString(4, user.gender);
    	preparedStatement.setString(5, user.username);
    	preparedStatement.setString(6, user.password);
    	
    	preparedStatement.executeUpdate();
    	
    	userRegistered = preparedStatement.executeUpdate() > 0;
    	
    	disconnect();
    	
    	preparedStatement.close();
    	
    	return userRegistered;
    }
    
    public static boolean update(Videos video) throws SQLException { //switch controller to ask which attribute needs to be updated?
        connect();
        
        String sql = "UPDATE videos SET title = ?, description = ?, date = ?, diy_question = ?, author = ? WHERE url = ?";
        
        preparedStatement = connect.prepareStatement(sql);
        
		preparedStatement.setString(1, video.title);
		preparedStatement.setString(2, video.description);
		preparedStatement.setString(3, video.date);
		preparedStatement.setString(4, video.diy_question);
		preparedStatement.setString(5, video.author);
		
		preparedStatement.executeUpdate();
		
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        
        preparedStatement.close();
        
        disconnect();
        
        return rowUpdated;     
    }
    
    public static boolean delete(String url) throws SQLException {
    	connect();
    	
        String sql = "DELETE FROM videos WHERE url = ?";        
        
        preparedStatement = connect.prepareStatement(sql);
        
        preparedStatement.setString(1, url);
        
        preparedStatement.executeUpdate();
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        
        preparedStatement.close();
        
        disconnect();
        
        return rowDeleted;     
    }
    
    public static boolean insert(Videos video) throws SQLException {
    	connect();
    	
    	String sql = "INSERT INTO videos(title, description, url, upload_date, diy_question, author) VALUES (?, ?, ?, ?, ?, ?)";
		
		preparedStatement = connect.prepareStatement(sql);
		
		preparedStatement.setString(1, video.title);
		preparedStatement.setString(2, video.description);
		preparedStatement.setString(3, video.url);
		preparedStatement.setString(4, video.date);
		preparedStatement.setString(5, video.diy_question);
		preparedStatement.setString(6, video.author);
		
		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        
        preparedStatement.close();
        
        disconnect();
        
        return rowInserted;
    }     
    
    public static List<Videos> listAllVideos() throws SQLException {
    	connect();
    	
        List<Videos> video_list = new ArrayList<Videos>();
        
        String sql = "SELECT * FROM videos"; 
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
        ResultSet resultSet = preparedStatement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String title = resultSet.getString("title");
        	String description = resultSet.getString("description");
        	String url = resultSet.getString("url");
        	String upload_date = resultSet.getString("upload_date");
        	String diy_question = resultSet.getString("diy_question");
        	String author = resultSet.getString("author");
             
            Videos video = new Videos(url, title, description, upload_date, diy_question, author);
            
            video_list.add(video);
        }        
        
        resultSet.close();
        preparedStatement.close();   
        
        disconnect();
        
        return video_list;
    }
}

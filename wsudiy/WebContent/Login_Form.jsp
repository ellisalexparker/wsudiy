<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head> <meta charset="UTF-8">
		<title>Welcome to WSU DIY</title>
	</head>
	<body>
		 <form action="${pageContext.request.contextPath}/loginview" method="post">
  			<div class="container">
    			<label for="uname"><b>Email</b></label>
   				<input type="text" placeholder="Enter Username" name="username" required>

    			<label for="psw"><b>Password</b></label>
    			<input type="password" placeholder="Enter Password" name="password" required>

    			<button type="submit">Login</button>
 			</div>
 			
 			<div class="container">
 				<a href="Sign_Up_Form.jsp">Need to make an Account? Click Here</a>
 			</div>
		</form> 	
	</body>
</html>
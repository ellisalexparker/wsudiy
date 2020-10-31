<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="UTF-8">
	<title>Create a WSU DIY Account</title>
	</head>
	
	<body>

	<form action="<%= request.getContextPath() %>/signupview" method="post">
	
  		<div class="container">
    	<h1>Sign Up</h1>
    	<p>Please fill in this form to create an account.</p>
    	<hr>
    	
    	<label><b>First Name</b></label>
    	<input type=text" placeholder="Enter First Name" name="fname" required>
    	
    	<label><b>Last Name</b></label>
    	<input type="text" placeholder="Enter Last Name" name="lname" required>
    	
    	<label><b>Birthday</b></label>
    	<input type ="text" placeholder="Enter Birthday" name="birthday" required>

    	<label><b>Email</b></label>
    	<input type="text" placeholder="Enter Email" name="email" required>

    	<label><b>Password</b></label>
    	<input type="password" placeholder="Enter Password" name="password" required>

    	<label><b>Repeat Password</b></label>
    	<input type="password" placeholder="Repeat Password" name="password-repeat" required>
		</div>
		
		<input type="submit" value="submit">
  		
	</form>

</body>
	
</html>
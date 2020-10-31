<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head> <meta charset="UTF-8">
		<title>Initialize WSU DIY</title>
	</head>
	<body>
		<h1>Click this button if you want to initialize the WSU DIY application: </h1>
		
		<p>When you click this button, it will do the following: </p>
		
		<ul>
			<li>Drop all existing tables</li>
			<li>All required tables will be recreated</li>
			<li>Each table will be inserted with 10 dummy entries</li>
		</ul>
		
		<form name="initialize" method="post" action="initialize">
			<input type="submit" value="Initialize Database">
		</form>
	</body>
</html>
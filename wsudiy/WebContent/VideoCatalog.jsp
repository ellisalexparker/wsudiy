<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>WSU DIY Application</title>
	</head>
	<body>
		<div align="center">
			<h3 align="center"> Video Catalog </h3>
			<table border="1" width="70%" align="center">
				<tr>
					<th>title</th>
					<th>url</th>
					<th>description</th>
					<th>date</th>
					<th>diy_question</th>
					<th>author</th>
				</tr>
				<c:forEach items="${listVideos}" var="video">
					<tr>
						<td>${video.title}</td>
						<td>${video.url}</td>
						<td>${video.description}</td>
						<td>${video.date}</td>
						<td>${video.diy_question}</td>
						<td>${video.author}</td>
					</tr>
				</c:forEach>
			</table>
    	</div>   
	</body>
</html>


<div align="center">
        	<table border="1" cellpadding="5">
            	<caption>List of Videos</caption>
            		<tr>
                		<th>Title</th>
                		<th>Author</th>
                		<th>URL</th>
                		<th></th>
                		<th>Actions</th>
            		</tr>
            	
            <c:forEach var="people" items="${listPeople}">
                <tr>
                    <td><c:out value="${people.id}" /></td>
                    <td><c:out value="${people.name}" /></td>
                    <td><c:out value="${people.address}" /></td>
                    <td><c:out value="${people.status}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${people.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${people.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
       		</table>
    	</div>   
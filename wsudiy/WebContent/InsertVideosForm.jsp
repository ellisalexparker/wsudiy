<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>WSU DIY Application</title>
	</head>
	
	<body>
    	<center>
        	<h1>People Management</h1>
        	<h2>
        		<a href="new">Add New People</a>
            	&nbsp;&nbsp;&nbsp;
            	<a href="list">List All People</a>     
        	</h2>
    	</center>
    	<div align="center">
            <form action="insert" method="post">
        		<table border="1" cellpadding="5">
            		<caption>
                		<h2>Add a Video to the Catalog:</h2>
            		</caption>
                <c:if test="${new_video != null}">
                    <input type="hidden" name="url" value="<c:out value='${new_video.url}' />" />
                </c:if>
                           
            	<tr>
                	<th>Title: </th>
                	<td>
                    	<input type="text" name="title" size="45" value="
                    	<c:out value='${video.title}' />"
                        />
                	</td>
            	</tr>
            	
           	 	<tr>
                	<th>Author: </th>
                <td>
                    <input type="text" name="author" size="45"
                            value="<c:out value='${new_video.author}' />"
                    />
                </td>
            	</tr>
            	
            	<tr>
                <th>: </th>
                <td>
                    <input type="text" name="status" size="5"
                            value="<c:out value='${people.status}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Book Share</title>
</head>
<body>
<h1>Change your Entry</h1>
<a href="/books">Back to the shelves</a>
<br>
<a href="/books/delete/${book.id}">Delete</a>
<br>
<form:form action="/books/edit/${book.id}" method="post" modelAttribute="book">
	<input type="hidden" name="_method" value="put">
	<form:input type="hidden" name="user" path="user"/>
	<form:input type="hidden" name="borrower" path="borrower"/>

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Title:</td>
	            <td class="float-left">
	            	<form:errors path="title" class="text-danger"/>
					<form:input class="input" path="title"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="float-left">Author:</td>
	            <td class="float-left">
	            	<form:errors path="author" class="text-danger"/>
					<form:input class="input" path="author"/>
	            </td>
	        </tr>        
	        <tr>
	            <td class="float-left">My Thoughts:</td>
	            <td class="float-left">
	            	<form:errors path="thoughts" class="text-danger"/>
					<form:textarea rows="3" class="input" path="thoughts"/>
	            </td>
	        </tr>   
	        <tr>
	        	<td colspan=2><input class="input" class="button" type="submit" value="Submit"/></td>
	        </tr>
	    </thead>
	</table>
	
	

</form:form>

</body>
</html>
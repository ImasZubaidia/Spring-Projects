<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Read Share</title>
</head>
<body>

<h1>Welcome <c:out value="${user.userName}"></c:out></h1>
<a href="/logout">Logout</a>
<p>Books from everyone's shelves:</p>
<p><a href="/newBook">+ Add to my shelf!</a></p>
<hr>
<table>
	<thead>
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Author Name</td>
			<td>Posted By</td>
		</tr>
	</thead>
    <tbody>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.id}"></c:out></td>
				<td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
				<td><c:out value="${book.author}"></c:out></td>
				<td><c:out value="${book.user.userName}"></c:out></td>
			</tr>	
		</c:forEach>
    </tbody>
</table>
</body>
</html>
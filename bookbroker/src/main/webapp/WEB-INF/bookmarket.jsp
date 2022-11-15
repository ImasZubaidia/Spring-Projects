<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Book Lender Dashboard</title>
</head>
<body>

<p>Hello <c:out value="${user.userName}"></c:out>. Welcome to</p>
<a href="/books">Back to shelves</a>
<p>Available books to borrow</p>
<hr>
<table>
	<thead>
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Author Name</td>
			<td>Owner</td>
			<td>Actions</td>
		</tr>
	</thead>
    <tbody>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.id}"></c:out></td>
				<td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
				<td><c:out value="${book.author}"></c:out></td>
				<td><c:out value="${book.user.userName}"></c:out></td>
				<c:if test = "${book.user.id==user.id}">
			       <td><a href="/books/edit/${book.id}">edit</a> <a href="/books/delete/${book.id}">delete</a></td>
			    </c:if>
				<c:if test = "${book.user.id!=user.id}">
			       <td><a href="/bookmarket/${book.id}">borrow</a></td>
			    </c:if>
			</tr>	
		</c:forEach>
    </tbody>
</table>
<p>Books I'm borrowing..</p>
<table>
	<thead>
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Author Name</td>
			<td>Owner</td>
			<td>Actions</td>
		</tr>
	</thead>
    <tbody>
		<c:forEach var="book" items="${myBooks}">
			<tr>
				<td><c:out value="${book.id}"></c:out></td>
				<td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
				<td><c:out value="${book.author}"></c:out></td>
				<td><c:out value="${book.user.userName}"></c:out></td>
				<td><a href="/bookmarket/return/${book.id}">return</a></td>
			</tr>	
		</c:forEach>
    </tbody>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${language.name}</title>
</head>
<body>
<p><c:out value="${language.name}"></c:out></p>
<p><c:out value="${language.creator}"></c:out></p>
<p><c:out value="${language.version}"></c:out></p>
<p><a href="/languages/${language.id}/edit">Edit</a></p>
<p><a href="/languages/${language.id}/delete">Delete</a></p>
<p><a href="/">Dashboard</a></p>
</body>
</html>
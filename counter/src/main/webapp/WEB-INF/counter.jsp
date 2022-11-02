<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Current visit count</title>
</head>
<body>
<p>You have visited <a href="/"><c:out value="${page}"/></a> <c:out value="${count}"/> time(s).</p>
<p><a href="/">Test another visit?</a></p>
<p><a href="/double-counter/">Add 2 visits</a></p>
<p><a href="/reset-counter/">Reset counter</a></p>
</body>
</html>
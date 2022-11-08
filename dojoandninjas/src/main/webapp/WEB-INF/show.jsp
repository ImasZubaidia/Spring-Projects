<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Dojo Page</title>
</head>
<body>
	<h3 class="jumbotron-heading">${dojo.name}</h3>
        <div class="table-1">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Age</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dojo.ninjas}" var="ninja">
                    <tr>
                        <td>${ninja.firstName}</td>
                        <td>${ninja.lastName}</td>
                        <td>${ninja.age}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

</body>
</html>
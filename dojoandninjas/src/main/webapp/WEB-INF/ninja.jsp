<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Ninja</title>
</head>
<body>
	<div id="container">
		<div id="leftpanel">
			<h1>New License</h1>
			<form:form action="/ninjas/new" method="post" modelAttribute="ninja">
				<h2>
			        <form:label  path="dojo">Person:</form:label>
			        <form:select  path="dojo">
						<c:forEach items="${dojos}" var="dojo">
							<form:option value="${dojo.id}"><c:out value="${dojo.name}"/></form:option>
						</c:forEach>
					</form:select>
			    </h2>	  
			    <h2>
			        <form:label  path="firstName">First Name:</form:label>
    			    <form:input  path="firstName"/>
    			    <form:errors path="firstName"/>
			    </h2>
			    <h2>
			        <form:label  path="lastName">Last Name:</form:label>
    			    <form:input  path="lastName"/>
    			    <form:errors path="lastName"/>
			    </h2>
			    <h2>
			        <form:label  path="age">Age:</form:label>
    			    <form:input  path="age"/>
    			    <form:errors path="age"/>
			    </h2>			    
			    <input id="btn" type="submit" value="Create"/>	 
			</form:form>
		</div>
	</div>

</body>
</html>
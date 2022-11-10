<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login or Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-sm mt-3">
        <div class="row justify-content-center">
            <div class="col-md-4 me-5 border p-4 mt-4">
            <h3>Register</h3>
                <form:form action="/register" method="post" modelAttribute="newUser">

                    
                    <form:input path="userName" type="text" class="form-control mt-4" placeholder="User name" />
					<form:errors path="userName" style="color:red;"/>

                    <form:input path="email" type="text" class="form-control mt-4" placeholder="Email" />
					<form:errors path="email" style="color:red;" />

                    <form:input path="password" type="password" class="form-control mt-4" placeholder="Password" />
                    <form:errors path="password" style="color:red;" />

                    <form:input path="confirm" type="password" class="form-control mt-4" placeholder="Confirm Passsword"/>
					<form:errors path="confirm" style="color:red;" />
					
                    <div class="mt-4">
					<button type="submit" class="btn btn-success btn-sm">Register</button>
					</div>
                </form:form>
            </div>

            <div class="col-md-4 me-5 border p-4 my-4">
            <h3>Login</h3>
                <form:form action="/login" method="post" modelAttribute="newLogin">
                    
                    
                    <form:input path="email" type="text" class="form-control mt-4" placeholder="Email" />
					<form:errors path="email" style="color:red;" />
                    
                    <form:input path="password" type="password" class="form-control mt-4" placeholder="Password" />
					<form:errors path="password" style="color:red;" />
					<div class="mt-4">
					<button type="submit" class="btn btn-success btn-sm">Log in</button>
					</div>
                    
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
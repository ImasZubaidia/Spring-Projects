<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Ninja Gold</title>
</head>
<body>
	<h3 class="left-h3">Your Gold :<c:out value="${gold}"/></h3>          
	<a href="/reset" class="startover"><button type="button" class="btn btn-outline-danger">Start Over</button></a>
    <div id="forms">
        <div class="form1">
            <form action="/process_money" method="post">
                <h3>Farm</h3>
                <p>(earns 10-20 Gold)</p>
                <input type="hidden" name="location" value="Farm">
                <button type="submit" class="btn btn-success" name="add" value="farm">Find Gold!</button>
            </form>
        </div>
        <div class="form1">
            <form action="/process_money" method="post">
                <h3>Cave</h3>
                <p>(earns 5-10 Gold)</p>
                <input type="hidden" name="location" value="Cave">
                <button type="submit" class="btn btn-success" name="add" value="cave">Find Gold!</button>
            </form>
        </div>
        <div class="form1">
            <form action="/process_money" method="post">
                <h3>House</h3>
                <p>(earns 2-5 Gold)</p>
                <input type="hidden" name="location" value="House">
                <button type="submit" class="btn btn-success" name="add" value="house">Find Gold!</button>
            </form>
        </div>
        <div class="form1">
            <form action="/process_money" method="post">
                <h3>Casino</h3>
                <p>(earns/takes 0-50 Gold)</p>
                <input type="hidden" name="location" value="Casino">
                <button type="submit" class="btn btn-success" name="add" value="casino">Find Gold!</button>
            </form>                        
        </div>
    </div><br><br>
     <div class="activity_log">
          <h3>Activity log:</h3>
          <div id="text_area">
            <c:forEach items="${activities}" var="activity">
            	<c:out value="${activity}" escapeXml="false" />
            </c:forEach>
                 
          </div>
          <br>
          <br>
     </div> 

</body>
</html>
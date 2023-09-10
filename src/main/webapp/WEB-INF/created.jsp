<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Created New Film</title>
</head>
<body>

<c:choose>
	<c:when test="${empty film }">
	<h1> film wasn't created invalid input </h1>
	</c:when>
	<c:otherwise>
	<h1>Film was created with id: ${film.id} </h1>
	
	</c:otherwise>
	</c:choose>
	<br>
	
	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
		to home</a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deleted</title>
</head>
<body>


	<c:choose>
		<c:when test="${!empty film.id }">
			<h1>${film} was deleted</h1>
		</c:when>
		<c:otherwise>
			<h1>${film}has NOT been deleted, please check the database and
				try again</h1>
		</c:otherwise>
	</c:choose>

	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
		to home</a>



	<h1>Movie has been deleted</h1>
	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
		to home</a>


</body>
</html>
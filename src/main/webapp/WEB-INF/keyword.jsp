<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Key Word Search</title>
</head>
<body>

<c:choose>
<c:when test="${! empty film}">
<h1>Your Key Word Search Found The Following Movies</h1>
<h3>Click on a movie title to be redirected the the movies information page:</h3>
	<c:forEach var="f" items="${film}">
		<ul>
			<li><a href="getFilm.do?filmId=${f.id}">${f.title}</a>
		</ul>
	</c:forEach>
</c:when>
<c:otherwise>
<h2>Your Keyword did not provide any results</h2>
</c:otherwise>
</c:choose>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>



	<c:choose>

		<c:when test="${! empty film}">
			<h1>Film Details</h1>
			<ul>
				<li><strong>Film ID: </strong>${film.id}</li>
				<li><strong>Title: </strong>${film.title}</li>
				<li><strong>Release Year: </strong>${film.releaseYear}</li>
				<li><strong>Length: </strong>${film.length}</li>
				<li><strong>Rating: </strong>${film.rating}</li>
				<li><strong>Special Features: </strong>${film.specialFeatures}</li>
				<li><strong>Language: </strong>${film.language}</li>
				<li><strong>Rental Rate: </strong>${film.rentalRate}</li>
				<li><strong>Replacement Cost: </strong>${film.replacementCost}</li>
				<li><strong>Description: </strong>${film.description}</li>
				<li><strong>Category: </strong>${film.categories}</li>
				<li><strong>Cast: </strong>${film.actors}</li>
			</ul>

			<br>
			<br>
			<br>
			<br>

			<a href="edit.do?filmId=${film.id}">Edit This Film</a>

			<br>
			<br>

			<a href="deleted.do?filmId=${film.id}">Delete This Film</a>

			


		</c:when>
		<c:otherwise>

			<br>
			<br>

			<p>No Film with that ID can be found</p>

			<br>
			<br>

		</c:otherwise>
	</c:choose>
	
	<br>
	<br>
	<br>
	<br>
	
	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
		to home</a>
</body>
</html>
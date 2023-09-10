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
				<li><strong>Category: </strong>${film.category}</li>
				<li><strong>Cast: </strong>${film.actors}</li>
			</ul>

			<a href="edit.html"><button type="button" class="btn btn-info">Edit</button></a>

			<br>
			<br>
			<form action="deleted.do" method="get" name="delete">

				<button type="button" class="btn btn-danger"
					onclick="alert('are you sure you want to delete?')" name="delete">Delete</button>

			</form>

			<br>
			<br>

			<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
				to home</a>
				
			<a href="deleted.do?filmId=${film.id}">Delete This Film</a>
	
		</c:when>
		<c:otherwise>
			<p>No Film with that ID can be found</p>
			
			
			
			<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
				to home</a>

		</c:otherwise>
	</c:choose>






	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back
		to home</a>





</body>
</html>
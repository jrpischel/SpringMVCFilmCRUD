<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>


<div
  class="bg-image p-5 text-center shadow-1-strong rounded mb-5 text-white"
  style="background-image: url('https://mdbcdn.b-cdn.net/img/new/slides/003.webp');"
>
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

			<a href="editForm.do?filmId=${film.id}"><button class="btn btn-info" type="button">Edit This Film</button></a>

			<br>
			<br>

			<a href="deleted.do?filmId=${film.id}"><button class="btn btn-danger" type="button" onclick="alert('Are you sure you would like to delete this film')">Delete This Film</button></a>

			


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
	
	<a href="findbyid.html" role="button" >Back
		to Find Film by ID</a>
	
	<br>
	<br>
	
	<a href="index.html" role="button" >Back
		to home</a>
</div>


	
</body>
</html>
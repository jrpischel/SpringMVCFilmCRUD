<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit film</title>
</head>
<body>

 <h1>Edit your film</h1>
	<p> "*" indicates required fields</p><br>

		<form action="edit.do" method="post">
		
		<input type="hidden" name="id" value="${film.id}"/> 

			<label for="title">*Title:</label>
			<input type="text" name="title" id="title" required value="${film.title}">

			<br>
			<br>
			<label for="description">Description:</label>
			<input type="text" name="description" id="description" value="${film.description}">

			<br>
			<br>
			<label for="releaseYear">Release Year:</label>
			<input type="number" name="releaseYear" id="releaseYear" value="${film.releaseYear}" min="1900" max="2099" step="1" >


			<br>
			<br>
			<label for="languageID">*Language ID:</label>
			<input type="number" name="languageID" id="languageID" value="1" min="1" max="6" step="1"value="${film.languageID}" required>
			<br>1-English 2-Italian 3-Japanese 4-Mandarin 5-French 6-German


			<br>
			<br>
			<label for="rentalRate">Rental Rate:</label>
			<input type="number" name="rentalRate" id="rentalRate" value="${film.rentalRate}" min="0.99" step="any">

			<br>
			<br>
			<label for="rentalDuration">Rental Duration:</label>
			<input type="number" name="rentalDuration" id="rentalDuration" value="${film.rentalDuration}" min="3" step="1">


			<br>
			<br>
			<label for="length">Length:</label>
			<input type="number" name="length" id="length" value="${film.length}">

			<br>
			<br>
			<label for="replacementCost">Replacement Cost:</label>
			<input type="number" name="replacementCost" id="replacementCost" value="${film.replacementCost}" min="19.99" step="1.00">

			<br>
			<br>
			<label for="rating">Rating:</label>
			<input type="text" name="rating" id="rating" value="${film.rating}">


			<br>
			<br>
			<label for="specialFeatures">Special Features:</label>
			<input type="text" name="specialFeatures" id="specialFeatures" value="${film.specialFeatures}">

				
			<br>
			<br>
			<input type="submit" />

		</form>


			<a href="index.html" role="button" class="btn btn-primary btn-lg">Back to home</a>
</body>
</html>
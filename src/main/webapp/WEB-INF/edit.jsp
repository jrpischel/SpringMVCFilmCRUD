<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit film</title>
</head>
<body>
 
 <h1>Current Film Details</h1>
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
 
 <h1>Edit your film</h1>
	<p> "*" indicates required fields</p><br>

		<form action="edit.do" method="post">
		
		<input type="hidden" name="id" value="${film.id}"/> 

			<label for="title">*Title:</label>
			<input type="text" name="title" id="title" required>

			<br>
			<br>
			<label for="description">Description:</label>
			<input type="text" name="description" id="description">

			<br>
			<br>
			<label for="releaseYear">Release Year:</label>
			<input type="number" name="releaseYear" id="releaseYear" value="2000" min="1900" max="2099" step="1" >


			<br>
			<br>
			<label for="languageID">*Language ID:</label>
			<input type="number" name="languageID" id="languageID" value="1" min="1" max="6" step="1" required>
			<br>1-English 2-Italian 3-Japanese 4-Mandarin 5-French 6-German


			<br>
			<br>
			<label for="rentalRate">Rental Rate:</label>
			<input type="number" name="rentalRate" id="rentalRate" value="4.99" min="0.99" step="any">

			<br>
			<br>
			<label for="rentalDuration">Rental Duration:</label>
			<input type="number" name="rentalDuration" id="rentalDuration" value="3" min="3" step="1">


			<br>
			<br>
			<label for="length">Length:</label>
			<input type="number" name="length" id="length" value="120">

			<br>
			<br>
			<label for="replacementCost">Replacement Cost:</label>
			<input type="number" name="replacementCost" id="replacementCost" value="19.99" min="19.99" step="1.00">

			<br>
			<br>
			<label for="rating">Rating:</label>
			<input type="text" name="rating" id="rating" value="PG">


			<br>
			<br>
			<label for="specialFeatures">Special Features:</label>
			<input type="text" name="specialFeatures" id="specialFeatures">

				
			<br>
			<br>
			<input type="submit" />

		</form>


			<a href="index.html" role="button" class="btn btn-primary btn-lg">Back to home</a>
</body>
</html>
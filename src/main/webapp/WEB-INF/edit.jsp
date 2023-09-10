<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit film</title>
</head>
<body>

<h3>Please edit the film categories</h3>
 
 <form action="getFilm.do" method="get">
	 Title <input type="text" name="edit" value="${film.title}">
	 <br>
	 Description <input type="text" name="edit" value="${film.description}">
	  <br>
	  Release Year <input type="number" name="edit" value="${film.releaseYear}">
	   <br>
	 Language ID <input type="number" name="edit" value="${film.language_id}">
	  <br>
	  Rental Duration <input type="number" name="edit" value="${film.title}">
	   <br>
	 Rental Rate <input type="number" name="edit" value="${film.title}">
	  <br>
	  Length <input type="number" name="edit" value="${film.title}">
	   <br>
	 Replacement Cost <input type="number" name="edit" value="${film.title}">
	  <br>
	 Rating <input type="text" name="edit" value="${film.title}">
	  <br>
	 Special Features <input type="text" name="edit" value="${film.title}">
	  <br>
	 <button type="submit">Submit</button>
</body>
</html>
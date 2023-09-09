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
        <li>${film.id}</li>
        <li>${film.title}</li>
        <li>${film.releaseYear}</li>
        <li>${film.languageID}</li>
        <li>${film.length}</li>
        <li>${film.rating}</li>
        <li>${film.specialFeatures}</li>
        <li>${film.language}</li>
        <li>${film.rentalRate}</li>
        <li>${film.replacementCost}</li>
        <li>${film.description}</li>
        <li>${film.actors}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No Film with that ID can be found</p>
    </c:otherwise>
  </c:choose>
  
  
  
</body>
</html>
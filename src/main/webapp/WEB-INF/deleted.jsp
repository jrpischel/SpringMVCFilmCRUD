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

<c:if test="${empty film }">
<h1> Movie has been deleted</h1>
</c:if>

${film} was not deleted

	<a href="index.html" role="button" class="btn btn-primary btn-lg">Back to home</a>

</body>
</html>
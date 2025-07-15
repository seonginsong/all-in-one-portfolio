<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>edit board</h1>
	<form action="/editBoard" method="post">
		title : <input type="text" name="title">
		<br>
		content : <input type="text" name="content">
		<br>
		<input type="hidden" name="id" value="${id}">
		<button type="submit">add</button>
	</form>
</body>
</html>
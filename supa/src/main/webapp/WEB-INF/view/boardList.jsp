<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>board List</h1>
	<table border="1">
		<tr>
			<th>title</th>
			<th>content</th>
			<th>create date</th>
			<th>update</th>
			<th>delete</th>
		</tr>
		<c:forEach var="b" items="${list}">
			<tr>
				<td>${b.title}</td>
				<td>${b.content}</td>
				<td>${b.createAt}</td>
				<td><a href="/editBoard?id=${b.id}">update</td>
				<td><a href="/deleteBoard?id=${b.id}">delete</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="/addBoard">추가</a>
</body>
</html>
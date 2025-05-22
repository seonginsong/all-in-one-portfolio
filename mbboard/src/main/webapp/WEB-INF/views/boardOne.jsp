<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>boardOne</h1>
<table border="1">
	<tr>
		<th>no</th>
		<th>title</th>
		<th>content</th>
		<th>user</th>
		<th>updatedate</th>
		<th>createdate</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<tr>
		<td>${board.boardNo}</td>
		<td>${board.boardTitle}</td>
		<td>${board.boardContent}</td>
		<td>${board.boardUser}</td>
		<td>${board.updatedate}</td>
		<td>${board.createdate}</td>
		<td><button type="button" id="goUpdate">수정</button></td>
		<td><button type="button" id="goDelete">삭제</button></td>
	</tr>
</table>
<a href="/boardList">리스트로</a>
<script>
	document.querySelector('#goUpdate').addEventListener('click', function() {
		location.href = "/updateBoard?boardNo=" + ${board.boardNo};
	});
	document.querySelector('#goDelete').addEventListener('click', function() {
		location.href = "/deleteBoard?boardNo=" + ${board.boardNo};
	});
</script>
</body>
</html>
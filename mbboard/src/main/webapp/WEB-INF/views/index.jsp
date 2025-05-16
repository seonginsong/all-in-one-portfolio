<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>전체</h2>
	<table border="1">
		<tr>
			<th>ANONYMOUS</th>
			<th>MEMBER</th>
			<th>ADMIN</th>
		</tr>
		<tr>
			<td>${connectCountMapAll.ANONYMOUS}</td>
			<td>${connectCountMapAll.MEMBER}</td>
			<td>${connectCountMapAll.ADMIN}</td>
		</tr>
	</table>
	<h2>오늘</h2>
	<table border="1">
		<tr>
			<th>ANONYMOUS</th>
			<th>USER</th>
			<th>ADMIN</th>
		</tr>
		<tr>
			<td>${connectCountMapToday.ANONYMOUS}</td>
			<td>${connectCountMapToday.MEMBER}</td>
			<td>${connectCountMapToday.ADMIN}</td>
		</tr>
	</table>
	<h2>현재 서버의 접속자(톰켓서버 안에 활성이된 세션의 개수)</h2>
	<table border="1">
		<tr>
			<th>TOTAL:</th>
		</tr>
		<tr>
			<td>${currentConnectCount}</td>
		</tr>
	</table>
</body>
</html>
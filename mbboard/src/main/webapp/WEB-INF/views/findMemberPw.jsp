<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${noIdEmail}">
		<script>
			alert('아이디나 이메일이 일치하지 않습니다.');
		</script>
	</c:if>
	<h1>findMemberPw</h1>
	<form action="findMemberPw" method="post">
		ID : <input type="text" name="memberId">
		<br>
		email : <input type="text" name="email">
		<br>
		<button type="submit">찾기</button>
	</form>
</body>
</html>
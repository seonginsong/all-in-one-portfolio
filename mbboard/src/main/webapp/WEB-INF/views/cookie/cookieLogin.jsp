<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/whitemono-style.css">

</head>
<body>
	<!-- 로그인이 안되어 있다면 -->
	<form method="post" action="/cookieLogin">
		<div>
			<div>memberId:</div>
			<div>
				<input type="text" name="memberId" value="${cookie.LoginMemberId.value}">
				<input type="checkbox" value="yes" name="saveIdCk"> 아이디 저장
			</div>
			<div>memberPw:</div>
			<div>
				<input type="password" name="memberPw">
			</div>
			<div>
				<button type="submit">로그인</button>
			</div>
		</div>
	</form>
	<div>
		<a href="/joinMember">회원가입</a>
	</div>

</body>
</html>

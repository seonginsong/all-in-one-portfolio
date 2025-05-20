<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${loginFail}">
	    <script>
	        alert("아이디 또는 비밀번호가 올바르지 않습니다.");
	    </script>
	</c:if>
	<c:if test="${loginMember == null}">
		<!-- 로그인이 안되어 있다면 -->
		<form method="post" action="/login">
			<div>
				<div>memberId:</div>
				<div>
					<input type="text" name="id">
				</div>
				<div>memberPw:</div>
				<div>
					<input type="password" name="pw">
				</div>
				<div>
					<button type="submit">로그인</button>
				</div>
			</div>
		</form>
		<div>
			<a href="/insertMember">회원가입</a>
		</div>
	</c:if>

	<c:if test="${loginMember != null}">
		<script>
			location.href = "/index";
		</script>
		<div>
			<a href="/logout">로그아웃</a>
		</div>
	</c:if>
</body>
</html>

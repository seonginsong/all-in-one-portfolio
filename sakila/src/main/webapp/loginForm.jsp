<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Integer staffId = (Integer)session.getAttribute("loginStaff");
	
	if(staffId != null) { //로그인 상태라면
		response.sendRedirect("/sakila/index.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Staff Login</h1>
	<form action="/sakila/loginAction.jsp" method="post">
	<!-- 데이터 값을 매개값으로 다른페이지로 전송하는 방법
		1) a태그 : get
		2) form태그의 method 속성 : get, post
	
	 -->
		<table border="1">
			<tr>
				<th>staffId</th>
				<td><input type="number" name="staffId"></td>
			</tr>
			<tr>
				<th>password</th>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>
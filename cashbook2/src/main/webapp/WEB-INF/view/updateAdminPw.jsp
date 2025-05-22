<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/cashbook2/css/style.css">
</head>
<body>
	<%
		if(request.getParameter("msg") != null) {
	%>
			<%=request.getParameter("msg")%>
	<%
		}
	%>
	<form action="<%=request.getContextPath()%>/updateAdminPw" method="post">
		<div>현재 비밀번호 : <input type="password" name="nowPw"></div>
		<div>변경 비밀번호 : <input type="password" name="nextPw1"></div>
		<div>비밀번호 확인 : <input type="password" name="nextPw2"></div>
	<button type="submit"> 변경하기</button>
	</form>
</body>
</html>
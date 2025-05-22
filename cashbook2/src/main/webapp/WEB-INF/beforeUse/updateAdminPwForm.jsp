<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/cashbook/css/style.css">
</head>
<body>
	<%
		if(request.getParameter("msg") != null) {
	%>
			<%=request.getParameter("msg")%>
	<%
		}
	%>
	<form action="/cashbook/updateAdminPwAction.jsp?adminId=<%=adminId%>">
		<div>현재 비밀번호 : <input type="password" name="nowPw"></div>
		<div>변경 비밀번호 : <input type="password" name="nextPw1"></div>
		<div>비밀번호 확인 : <input type="password" name="nextPw2"></div>
	<button type="submit"> 변경하기</button>
	</form>
</body>
</html>
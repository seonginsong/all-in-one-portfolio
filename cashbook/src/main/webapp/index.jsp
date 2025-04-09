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
</head>
<body>
	<a href="/cashbook/logoutAction.jsp">로그아웃</a> | <a href="updateAdminPwForm.jsp">비밀번호 변경</a>
	<h1><%=adminId%>님 환영합니다</h1>
	
</body>
</html>
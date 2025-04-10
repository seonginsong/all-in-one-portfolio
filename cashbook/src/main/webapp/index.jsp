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
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1><%=adminId%>님 환영합니다</h1>
	1. <a href="/cashbook/categoryList.jsp">카테고리 목록</a>
	2. <a href="">월별 cash 목록</a>
	3. <a href="">일별 cash 목록</a>
	4. <a href="">cash 상세보기</a>
</body>
</html>
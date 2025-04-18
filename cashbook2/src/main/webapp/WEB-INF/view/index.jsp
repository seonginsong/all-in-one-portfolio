<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/cashbook2/css/style.css">
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1><%=request.getAttribute("adminId")%>님 환영합니다</h1>
	1. <a href="">카테고리 목록</a>
	2. <a href="">월별 cash 목록</a>
	3. <a href="">통계</a>
</body>
</html>
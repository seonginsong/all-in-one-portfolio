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
	<h1>Category 입력</h1>
		<%
			if(request.getParameter("umsg") != null) {
		%>		
			<div>Title이 이미 존재해서 추가가 불가능합니다.</div>
		<%
			}
		%>
		<%
			if(request.getParameter("msg") != null) {
		%>		
			<div>입력을 하지 않았습니다.</div>
		<%
			}
		%>
	<form action="/cashbook/insertCategoryAction.jsp">
		<table border="1">
			<tr>
				<th>kind</th>
				<th>title</th>
			</tr>
			<tr>
				<td>
				<input type="radio" name="kind" value="수입">수입
				<input type="radio" name="kind" value="지출">지출
				<td><input type="text" name="title"></td>
			</tr>
		</table>
		<div align="center"><button type="submit">추가하기</button></div>
	</form>
</body>
</html>
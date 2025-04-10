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
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
<title></title>
<link rel="stylesheet" href="/cashbook/css/style.css">
</head>
<body>
	<h1>Category 입력</h1>
	<form action="/cashbook/insertCategoryAction.jsp">
		<table border="1">
			<tr>
				<th>kind</th>
				<th>title</th>
			</tr>
			<tr>
				<td>
				<select name="kind">
					<option value="수입">수입</option>
					<option value="지출">지출</option>
				</select></td>
				<td><input type="text" name="title"></td>
			</tr>
		</table>
		<div align="center"><button type="submit">추가하기</button></div>
	</form>
</body>
</html>
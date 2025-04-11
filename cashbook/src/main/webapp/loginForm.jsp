<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = "admin";
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
	<h1>로그인</h1>
	<form action="/cashbook/loginAction.jsp">
		<table border="1">
			<tr>
				<th>아이디 : <input type="text" name="adminId" value="<%=adminId%>" readonly></th>
			</tr>
			<tr>
				<th>비밀번호 : <input type="password" name="adminPw"> <button type="submit">로그인</button></th>
			</tr>
		</table>
	</form>
</body>
</html>
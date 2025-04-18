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
			비밀번호가 다릅니다.
	<%
		}
	%>
	<h1>로그인</h1>
	<form action="<%=request.getContextPath()%>/login" method="post">
		<table border="1">
			<tr>
				<th>아이디 : </th>
				<td><input type="text" name="adminId" value="admin" readonly></td>
			</tr>
			<tr>
				<th>비밀번호 : </th>
				<td><input type="password" name="adminPw"> <button type="submit">로그인</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
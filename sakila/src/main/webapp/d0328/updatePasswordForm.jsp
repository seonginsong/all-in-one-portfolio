<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");

	if(staffId == null) { //로그아웃 상태라면
		response.sendRedirect("/sakila/loginForm.jsp");
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
	<form action="/sakila/d0328/updatePasswordAction.jsp">
		<table border="1">
				<tr>
					<th>현재 비밀번호</th>
					<td> <input type="password" name="nowPassword"></td>
				</tr>
				<tr>
					<th>변경 비밀번호</th>
					<td><input type="password" name="nextPassword1"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="nextPassword2"></td>
				</tr>
		</table>
		<button type="submit">변경하기</button>
	</form>
	
</body>
</html>
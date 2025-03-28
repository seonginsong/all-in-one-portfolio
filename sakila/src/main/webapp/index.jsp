<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");
	String firstName = (String)session.getAttribute("loginFirstName");	

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
	<div>
		<%=firstName%>님 반갑습니다
		<a href="/sakila/logout.jsp">[로그아웃]</a>
		<a href="/sakila/d0328/updatePasswordForm.jsp">[비밀번호수정]</a>
	</div>
	<hr>
	<h1>Index</h1>
	<ol>
		<li><a href="/sakila/d0325/rentalList.jsp">대여목록</a></li>
		<li><a href="/sakila/d0326/actorList.jsp">배우목록</a></li>
		<li><a href="/sakila/d0326/filmList.jsp">영화목록</a></li>
		<li><a href="/sakila/d0327/inventoryList.jsp">인벤토리목록</a></li>
	</ol>
</body>
</html>
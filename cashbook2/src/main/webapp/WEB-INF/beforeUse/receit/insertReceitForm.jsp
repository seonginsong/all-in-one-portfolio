<%@page import="model.ReceitDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
	
	
	int cashNo = Integer.parseInt(request.getParameter("cashNo"));	
	System.out.println(cashNo);
	
	ReceitDao receitDao = new ReceitDao();
	int cnt = receitDao.selectReceitCount(cashNo);
	if(cnt>0) {
		response.sendRedirect("/cashbook/cash/cashOne.jsp?cashNo="+cashNo+"&cmsg=error");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/cashbook/css/style.css">
		<title>영수증 올리기</title>
	</head>
<body>
	<%	
		if(request.getParameter("msg") != null) { // insertImageAction png,jpg파일이 아닐 경우에만 msg값이 넘어옴
	%>
		<div>png나 jpg파일이 아닙니다.</div>
	<%
		}
	%>
	<div>
		<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<form action="/cashbook/receit/insertReceitAction.jsp?cashNo=<%=cashNo%>" method="post" enctype="multipart/form-data">
		<div>영수증 : <input type="file" name="receitFile" /></div>
		<button type="submit">이미지 등록</button>
    </form>
</body>
</html>
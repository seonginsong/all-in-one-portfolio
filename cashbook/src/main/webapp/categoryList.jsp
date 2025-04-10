<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}

	String searchWord = "";
	if(request.getParameter("searchWord") != null) {
		searchWord = request.getParameter("searchWord");
	}
	
	String kind = "";
	
	// 페이징
	int currentPage = 1;
	int rowPerPage = 10;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	Paging p = new Paging();
	p.setCurrentPage(currentPage);
	p.setRowPerPage(rowPerPage);
		
	CategoryDao categoryDao = new CategoryDao();
	
	int totalRow = categoryDao.getTotalCategory(searchWord, kind);
	int lastPage = p.getLastPage(totalRow);

	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
<title></title>
</head>
<body>
	
</body>
</html>
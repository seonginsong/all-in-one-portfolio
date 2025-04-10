<%@page import="dto.Category"%>
<%@page import="model.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}


	int cgno = Integer.parseInt(request.getParameter("category_no"));
	
	CategoryDao categoryDao = new CategoryDao();
	Category c = new Category();
	
	c = categoryDao.selectCategoryOne(cgno);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook/css/style.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>Category Title 수정</h1>
		<%
			if(request.getParameter("umsg") != null) {
		%>		
			<div>Title이 이미 존재해서 변경이 불가능합니다.</div>
		<%
			}
		%>
	<form action="/cashbook/updateCategoryTitleAction.jsp">
		<table border="1">
			<tr>
				<th>현재 title</th>
				<th>변경할 title</th>
			</tr>
			<tr>
				<td>
				<input type="hidden" name="category_no" value="<%=cgno%>">
				<input type="text" name="previousTitle" value="<%=c.getTitle()%>" readonly>
				<td><input type="text" name="title"></td>
			</tr>
		</table>
		<div align="center"><button type="submit">변경하기</button></div>
	</form>
</body>
</html>
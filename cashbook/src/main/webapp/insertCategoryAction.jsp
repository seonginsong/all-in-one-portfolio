<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dto.*" %>
<%
	String kind = request.getParameter("kind");
	String title = request.getParameter("title");
	
	CategoryDao categoryDao = new CategoryDao();
	int ckCnt = categoryDao.selectCntKindTitle(title, kind);
	
	
	
	if(ckCnt > 0) {
		response.sendRedirect("/cashbook/insertCategoryForm.jsp?umsg=SameTitle");
	} else {
		Category c = new Category();
		c.setKind(kind);
		c.setTitle(title);
		
		categoryDao.insertCategory(c);
		response.sendRedirect("/cashbook/categoryList.jsp");
	}
%>
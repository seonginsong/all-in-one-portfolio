<%@page import="dto.Category"%>
<%@page import="model.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int category_no = Integer.parseInt(request.getParameter("category_no"));
	String title = request.getParameter("title");
	CategoryDao categoryDao = new CategoryDao();
	Category c = new Category();
	c = categoryDao.selectCategoryOne(category_no);
	int ckCnt = categoryDao.selectCntKindTitle(title, c.getKind());
	System.out.println(ckCnt);
	if(ckCnt > 0) {
		response.sendRedirect("/cashbook/updateCategoryTitleForm.jsp?umsg=SameTitle&category_no="+category_no);
	} else {
		categoryDao.updateCategoryTitle(title, category_no);
		response.sendRedirect("/cashbook/categoryList.jsp");
	}
%>
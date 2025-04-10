<%@page import="model.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int cgno = Integer.parseInt(request.getParameter("category_no"));
	String title = request.getParameter("title");
	CategoryDao categoryDao = new CategoryDao();
	categoryDao.updateCategoryTitle(title, cgno);
	response.sendRedirect("/cashbook/categoryList.jsp");
%>
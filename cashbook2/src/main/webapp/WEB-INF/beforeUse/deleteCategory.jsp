<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int cgno = Integer.parseInt(request.getParameter("category_no"));

	CategoryDao categoryDao = new CategoryDao();
	
	CashDao cashDao = new CashDao();
	int cnt = cashDao.selectCountCash(cgno);
	System.out.println(cnt);
	if(cnt == 0) {
		categoryDao.deleteCategory(cgno);
		response.sendRedirect("/cashbook/categoryList.jsp");
	} else {
		response.sendRedirect("/cashbook/categoryList.jsp?msg=CashIsExist&cgno="+cgno);
	}
%>
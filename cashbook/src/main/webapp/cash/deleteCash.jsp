<%@page import="model.CashDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int cashNo = Integer.parseInt(request.getParameter("cashNo"));
	int y = Integer.parseInt(request.getParameter("y"));
	int m = Integer.parseInt(request.getParameter("m"));
	int d = Integer.parseInt(request.getParameter("d"));


	CashDao cashDao = new CashDao();
	cashDao.deleteCash(cashNo);
	response.sendRedirect("/cashbook/dateList.jsp?y="+y+"&m="+m+"&d="+d);
%>
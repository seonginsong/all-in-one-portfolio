<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="dto.*"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	ItemDao itemDao = new ItemDao();
	QuestionDao questionDao = new QuestionDao();
	
	itemDao.deleteItem(num);
	questionDao.deleteQuestion(num);
	
	response.sendRedirect("/poll/pollList.jsp");
%>
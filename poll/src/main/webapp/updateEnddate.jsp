<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%
	String enddate = request.getParameter("enddate");
	int num = Integer.parseInt(request.getParameter("num"));
	
	QuestionDao questionDao = new QuestionDao();
	questionDao.updateEnddate(enddate, num);
	System.out.println("num: " + request.getParameter("num"));
    System.out.println("enddate: " + request.getParameter("enddate"));
	
	response.sendRedirect("/poll/pollList.jsp");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pass = request.getParameter("pass");
	System.out.println(pass);
	BoardDao boardDao = new BoardDao();

	boardDao.deleteBoard(num, pass);
	response.sendRedirect("/poll/board/boardList.jsp");
	
%>
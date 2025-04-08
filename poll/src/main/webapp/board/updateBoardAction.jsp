<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String pass = request.getParameter("pass");
	Board b = new Board();
	b.setName(name);
	b.setSubject(subject);
	b.setContent(content);
	
	BoardDao boardDao = new BoardDao();
	boardDao.updateBoard(b, num, pass);
	
	response.sendRedirect("/poll/board/boardOne.jsp?num="+num);
%>
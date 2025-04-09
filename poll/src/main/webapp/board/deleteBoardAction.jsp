<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pass = request.getParameter("pass");
	System.out.println(pass);
	BoardDao boardDao = new BoardDao();
	
	
	Board b = boardDao.selectBoardOne(num);
	System.out.println("b.getPass() :"+b.getPass());
	if(!b.getPass().equals(pass)) {
		response.sendRedirect("/poll/board/deleteBoardForm.jsp?num="+num+"&msg=PasswordError");
		return;
	} else {
		boardDao.deleteBoard(num, pass);
		response.sendRedirect("/poll/board/boardList.jsp");
	}
	
	
%>
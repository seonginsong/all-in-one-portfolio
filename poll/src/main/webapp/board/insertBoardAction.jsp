<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%
	// Controller Layer(request 분석, model 호출)
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String pass = request.getParameter("pass");
	
	// 답글이면 부모글pk을 받아옴, 아니면 본인pk
	int ref = 0; // 답글이 아니면 0을 입력하고 입력 직후에 본인의 num값(pk)과 동일하게
	if(request.getParameter("ref") != null) {
		Integer.parseInt(request.getParameter("ref"));
	}
	
	// Form 입력타입(DTO 사용가능)으로 묶음
	Board board = new Board();
	board.setName(name);
	board.setSubject(subject);
	board.setContent(content);
	board.setRef(ref);
	board.setPass(pass);
	board.setIp(request.getRemoteAddr()); // 다른 API를 이용하여 IP를 구하는 경우가 많음
	
	// logging(디버깅,.....)
	System.out.println(board.toString()); // System.out.println(board) 로 생략 가능

	BoardDao boardDao = new BoardDao();
	boardDao.insertBoard(board);
	
	// 뷰가 있다면 뷰를 연결(디스패츠 ex : include, forward), 뷰X->클라이언트에 다른요청(redirect~)
	response.sendRedirect("/poll/board/boardList.jsp");

%>
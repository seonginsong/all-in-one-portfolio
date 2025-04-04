<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%
	String[] inumArr = request.getParameterValues("inum");
	int qnum = Integer.parseInt(request.getParameter("qnum"));
	// inumArr 개수만큼 count++ 하는 메서드를 호출
	ItemDao itemDao = new ItemDao();
	for(String inum : inumArr) {
		itemDao.updateItemCountPlus(qnum, Integer.parseInt(inum));
	}
	// View 존재하지 않는 프로세스 : JSP(Java Server Page)일 필요가 없다 -> 다른 요청이 필요 -> Backend(Server)에서 요청 불가 -> 브라우저에 요청강제화(<a> 강제화)
	response.sendRedirect("/poll/pollList.jsp");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dto.*" %>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	String nowPw = request.getParameter("nowPw");
	String nextPw1 = request.getParameter("nextPw1");
	String nextPw2 = request.getParameter("nextPw2");
	
	AdminDao adminDao = new AdminDao();
	Admin a = adminDao.selectAdmin(adminId);
	if(nowPw.equals(a.getAdmin_pw()) && nextPw1.equals(nextPw2) && !nowPw.equals(nextPw1)) {
		adminDao.updatePw(nextPw1, adminId);
		session.invalidate();
		response.sendRedirect("/cashbook/loginForm.jsp");
	} else {
		response.sendRedirect("/cashbook/updateAdminPwForm.jsp?msg=PasswordError");
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dto.*" %>
<%
	String id = request.getParameter("admin_id");
	String pw = request.getParameter("admin_pw");
	System.out.println(id);
	System.out.println(pw);
	
	AdminDao adminDao = new AdminDao();
	int loginOk = adminDao.selectLogin(id, pw);
	System.out.println("loginOk :"+loginOk);
	
	if(loginOk == 1) {
		session.setAttribute("loginId", id);
		response.sendRedirect("/cashbook/index.jsp");
	} else {
		response.sendRedirect("/cashbook/loginForm.jsp?msg=PasswordError");
	}
%>
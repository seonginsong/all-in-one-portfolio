<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");

	if(staffId == null) { //로그아웃 상태라면
		response.sendRedirect("/sakila/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
%>
<%
	String nowPassword = request.getParameter("nowPassword");
	String nextPassword1 = request.getParameter("nextPassword1");
	String nextPassword2 = request.getParameter("nextPassword2");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	
	String sql1 = "update staff set password = ? where staff_id = ? and password = ?";
	
	if(nextPassword1.equals(nextPassword2)) {
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, nextPassword1);
		stmt1.setInt(2, staffId);
		stmt1.setString(3, nowPassword);
		int result = stmt1.executeUpdate();
		
		if(result > 0) {
			session.invalidate();
			response.sendRedirect("/sakila/loginForm.jsp");
		} else {
			response.sendRedirect("/sakila/d0328/updatePasswordForm.jsp");
		}
	}	else {
		response.sendRedirect("/sakila/d0328/updatePasswordForm.jsp");
	}






%>
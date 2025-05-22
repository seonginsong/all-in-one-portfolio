<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");
	Integer customerId = Integer.parseInt(request.getParameter("customerId"));
	Integer active = Integer.parseInt(request.getParameter("active"));
	
	if(staffId == null) { //로그아웃 상태라면
		response.sendRedirect("/sakila/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
	

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	
	String sql1 = "update customer set active = 1 where customer_id = ?";
	
	if(active == 1) {
		sql1 = "update customer set active = 0 where customer_id = ?";
	}
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, customerId);
	stmt1.executeUpdate();
	
		
	response.sendRedirect("/sakila/d0327/inventoryList.jsp");

%>

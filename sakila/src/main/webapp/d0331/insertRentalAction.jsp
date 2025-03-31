<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");
	Integer inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
	Integer customerId = Integer.parseInt(request.getParameter("customerId"));
	
	if(staffId == null) { //로그아웃 상태라면
		response.sendRedirect("/sakila/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	
	String sql1 = "insert into rental(rental_date,inventory_id,customer_id,staff_id) values(now(),?,?,?)";
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, inventoryId);
	stmt1.setInt(2, customerId);
	stmt1.setInt(3, staffId);
	
	stmt1.executeUpdate();
	
	response.sendRedirect("/sakila/d0327/inventoryList.jsp");



%>

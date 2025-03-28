<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	// controller layer : staffId, password
	int staffId = Integer.parseInt(request.getParameter("staffId"));
	String password = request.getParameter("password");
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	
	String sql = "select staff_id staffId, first_name firstName from staff where staff_id = ? and password = ?";
	stmt1 = conn.prepareStatement(sql);
	stmt1.setInt(1, staffId);
	stmt1.setString(2, password);
	
	rs1 = stmt1.executeQuery();

	if(rs1.next()) {
		//로그인 성공
		// isLogin = true;
		System.out.println("로그인 성공");
		// 현재세션영역에 loginStaff변수를 생성(이 변수가 접속자의 세션에 있으면 로그인 상태, 없다면 로그아웃상태)
		session.setAttribute("loginStaff", rs1.getInt("staffId"));
		session.setAttribute("loginFirstName", rs1.getString("firstName"));
		response.sendRedirect("/sakila/index.jsp");
	}	else {
		//로그인 실패
		System.out.println("로그인 실패");
		response.sendRedirect("/sakila/loginForm.jsp");
	}
%>
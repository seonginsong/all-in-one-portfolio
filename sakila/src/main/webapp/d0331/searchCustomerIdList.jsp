<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	//로그인 되었는지 아닌지?
	Integer staffId = (Integer)session.getAttribute("loginStaff");

	if(staffId == null) { //로그아웃 상태라면
		response.sendRedirect("/sakila/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}

	String searchName = request.getParameter("searchName");
	
	// from customer where first_name like ? or last_name like ?

	Integer inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	String sql1 = "select customer_id customerId, first_name firstName, last_name lastName, email, active from customer where concat(first_name, last_name) like ?";
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setString(1, "%" + searchName + "%");
	System.out.println(stmt1);
	rs1 = stmt1.executeQuery();





%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<table border="1">
		<tr>
			<td>customerId</td>
			<td>firstName</td>
			<td>lastName</td>
			<td>email</td>
			<td>active</td>
			<td>선택</td>
		</tr>
		<%
			while(rs1.next()) {
		%>
				<tr>
					<td><%=rs1.getInt("customerId")%></td>
					<td><%=rs1.getString("firstName")%></td>
					<td><%=rs1.getString("lastName")%></td>
					<td><%=rs1.getString("email")%></td>
					<td><%=rs1.getInt("active")%></td>
					<td>
						<%
							if(rs1.getInt("active") == 0) {
						%>
								<a href='/sakila/d0331/updateCustomerActive.jsp?customerId=<%=rs1.getInt("customerId")%>&active=<%=rs1.getInt("active")%>'>휴면상태해지하기</a><!-- customer.active 값을 0에서 1로 변경 -->
						<%
							} else {
						%>		
								<a href='/sakila/d0331/insertRentalForm.jsp?customerId=<%=rs1.getInt("customerId")%>&inventoryId=<%=inventoryId%>'>선택</a>
						<%
							}
						%>
					</td>
				</tr>
		<%				
			}
		%>
	</table>
</body>
</html>
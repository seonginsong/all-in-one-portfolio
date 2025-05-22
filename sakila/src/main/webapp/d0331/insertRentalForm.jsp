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
	/*
	rental_id : 자동
	rental_date : 지금 날짜 curdate() or now() or sysdate...
	inventory_id : 넘어옴
	customer_id : 직접입력
	return_date : 널
	staff_id : staffId
	*/
	Integer inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
	Integer customerId = null;
	if(request.getParameter("customerId") != null) {
		// 이름 검색 후 customerListByName.jsp에 다녀오면 customerId가 채워짐
		customerId = Integer.parseInt(request.getParameter("customerId"));
	}
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	String sql1 = "select i.inventory_id inventoryId, i.film_id filmId, f.title title, i.store_id storeId from inventory i inner join film f on i.film_id = f.film_id where inventory_id = ?";
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, inventoryId);
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
	<h1>Insert Rental Inventory</h1>
	<%
		if(rs1.next()) {
	%>
		<form action="/sakila/d0331/searchCustomerIdList.jsp" method="post">
			<input type="hidden" name="inventoryId" value="<%=inventoryId%>">
			<input type="text" name="searchName">
			<button type="submit">이름으로 customerId검색</button>
		</form>
		<form action="/sakila/d0331/insertRentalAction.jsp" method="post">		
			<table border="1">
				<tr>
					<td>customerId</td>
					<td><input type="text" name="customerId" value="<%=customerId%>" readonly></td>
				</tr>
				<tr>
					<td>inventoryId</td>
					<td><input type="text" name="inventoryId" value="<%=inventoryId%>" readonly></td>
				</tr>
				<tr>
					<td>filmId</td>
					<td><input type="text" name="inventoryId" value="<%=rs1.getInt("filmId")%>" readonly>/
					<a href="/sakila/d0326/filmOne.jsp?title=<%=rs1.getString("title")%>"><%=rs1.getString("title")%></a></td>
				</tr>
				<tr>
					<td>storeId</td>
					<td><input type="text" name="storeId" value="<%=rs1.getInt("storeId")%>" readonly></td>
				</tr>
				<tr>
					<td>staffId</td>
					<td><input type="text" name="staffId" value="<%=staffId%>" readonly></td>
				</tr>
			</table>
			<button type="submit">대여하기</button>
		</form>
	<%
		}
	%>
</body>
</html>
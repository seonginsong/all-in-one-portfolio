<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
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
	//페이징
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10;
	int startRow = (currentPage-1) * rowPerPage;
	//검색어
	String searchWord = request.getParameter("searchWord");
	if(searchWord == null) {
		searchWord = "";
	}
	//지점
	String storeId = request.getParameter("storeId");
	int sid = 0;
	if(storeId != null) {
		sid = Integer.parseInt(storeId);
	} else {
		sid = 0;
	}
	//[1], [2]...를 위한 시작페이지, 끝페이지
	int startPage = (((currentPage - 1) / rowPerPage) * rowPerPage) + 1;
	int endPage = startPage + rowPerPage - 1;
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");

	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	String sql1 = "select count(*) cnt from film f "
					+"inner join inventory i on f.film_id = i.film_id "
					+"inner join rental r on i.inventory_id = r.inventory_id "
					+"inner join customer c on r.customer_id = c.customer_id "
					+"inner join staff sf on r.staff_id = sf.staff_id "
					+"inner join store se on se.store_id = sf.store_id "
					+"where se.store_id = ? and f.title LIKE ?";
	//총 페이지 수를 구하기 위한 count - 검색어가 없을때 sid=0,1,2 검색어가 있을때 sid=0,1,2 가장 간결화 : sid = 0 -> 검색어 없 있, sid = 1...			
	if(sid == 0) {
		if(searchWord == "") {
			sql1 = "select count(*) cnt from film f "
					+"inner join inventory i on f.film_id = i.film_id "
					+"inner join rental r on i.inventory_id = r.inventory_id "
					+"inner join customer c on r.customer_id = c.customer_id "
					+"inner join staff sf on r.staff_id = sf.staff_id "
					+"inner join store se on se.store_id = sf.store_id ";
			stmt1 = conn.prepareStatement(sql1);
		}	else {
				sql1 = "select count(*) cnt from film f "
						+"inner join inventory i on f.film_id = i.film_id "
						+"inner join rental r on i.inventory_id = r.inventory_id "
						+"inner join customer c on r.customer_id = c.customer_id "
						+"inner join staff sf on r.staff_id = sf.staff_id "
						+"inner join store se on se.store_id = sf.store_id "
						+"where f.title like ?";
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setString(1, "%" + searchWord + "%");
		}
	}	else {
			if(searchWord == "") {
				sql1 = "select count(*) cnt from film f "
						+"inner join inventory i on f.film_id = i.film_id "
						+"inner join rental r on i.inventory_id = r.inventory_id "
						+"inner join customer c on r.customer_id = c.customer_id "
						+"inner join staff sf on r.staff_id = sf.staff_id "
						+"inner join store se on se.store_id = sf.store_id "
						+"where sf.store_id = ?";
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setInt(1, sid);
			}	else {
					sql1 = "select count(*) cnt from film f "
							+"inner join inventory i on f.film_id = i.film_id "
							+"inner join rental r on i.inventory_id = r.inventory_id "
							+"inner join customer c on r.customer_id = c.customer_id "
							+"inner join staff sf on r.staff_id = sf.staff_id "
							+"inner join store se on se.store_id = sf.store_id "
							+"where sf.store_id = ? and f.title like ?";
					stmt1 = conn.prepareStatement(sql1);
					stmt1.setInt(1, sid);
					stmt1.setString(2, "%" + searchWord + "%");
			}
	}	

	rs1 = stmt1.executeQuery();
	//[1],[2]...페이지
	int totalCnt = 0;
	if(rs1.next()) {
		totalCnt = rs1.getInt("cnt");
	}
	int lastPage = totalCnt / rowPerPage;
	if(totalCnt % rowPerPage != 0) {
		lastPage = lastPage +1;
	}

	if(endPage > lastPage) {
		endPage = lastPage;
	}
	
	PreparedStatement stmt2 = null;
	ResultSet rs2 = null;
	
	String sql2 = "select r.rental_id rentalId, f.title title, i.inventory_id inventoryId"
			+", c.first_name fName, c.last_name lName, c.customer_id customerId"
			+", se.store_id storeId, r.rental_date rentalDate, r.return_date returnDate from film f "
			+"inner join inventory i on f.film_id = i.film_id "
			+"inner join rental r on i.inventory_id = r.inventory_id "
			+"inner join customer c on r.customer_id = c.customer_id "
			+"inner join staff sf on r.staff_id = sf.staff_id "
			+"inner join store se on se.store_id = sf.store_id "
			+"where se.store_id = ? and f.title LIKE ? "
			+"order by r.rental_date desc limit ?, ?";
	
	if(sid == 0) {
		if(searchWord == "") {
			sql2 = "select r.rental_id rentalId, f.title title, i.inventory_id inventoryId"
					+", c.first_name fName, c.last_name lName, c.customer_id customerId"
					+", se.store_id storeId, r.rental_date rentalDate, r.return_date returnDate from film f "
					+"inner join inventory i on f.film_id = i.film_id "
					+"inner join rental r on i.inventory_id = r.inventory_id "
					+"inner join customer c on r.customer_id = c.customer_id "
					+"inner join staff sf on r.staff_id = sf.staff_id "
					+"inner join store se on se.store_id = sf.store_id "
					+"order by r.rental_date desc limit ?, ?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, startRow);
			stmt2.setInt(2, rowPerPage);
		}	else {
				sql2 = "select r.rental_id rentalId, f.title title, i.inventory_id inventoryId"
						+", c.first_name fName, c.last_name lName, c.customer_id customerId"
						+", se.store_id storeId, r.rental_date rentalDate, r.return_date returnDate from film f "
						+"inner join inventory i on f.film_id = i.film_id "
						+"inner join rental r on i.inventory_id = r.inventory_id "
						+"inner join customer c on r.customer_id = c.customer_id "
						+"inner join staff sf on r.staff_id = sf.staff_id "
						+"inner join store se on se.store_id = sf.store_id "
						+"where f.title LIKE ? "
						+"order by r.rental_date desc limit ?, ?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setString(1, "%" + searchWord + "%");
				stmt2.setInt(2, startRow);
				stmt2.setInt(3, rowPerPage);
		}
	}	else {
		if(searchWord == "") {
			sql2 = "select r.rental_id rentalId, f.title title, i.inventory_id inventoryId"
					+", c.first_name fName, c.last_name lName, c.customer_id customerId"
					+", se.store_id storeId, r.rental_date rentalDate, r.return_date returnDate from film f "
					+"inner join inventory i on f.film_id = i.film_id "
					+"inner join rental r on i.inventory_id = r.inventory_id "
					+"inner join customer c on r.customer_id = c.customer_id "
					+"inner join staff sf on r.staff_id = sf.staff_id "
					+"inner join store se on se.store_id = sf.store_id "
					+"where se.store_id = ? "
					+"order by r.rental_date desc limit ?, ?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, sid);
			stmt2.setInt(2, startRow);
			stmt2.setInt(3, rowPerPage);
	}	else {
		sql2 = "select r.rental_id rentalId, f.title title, i.inventory_id inventoryId"
				+", c.first_name fName, c.last_name lName, c.customer_id customerId"
				+", se.store_id storeId, r.rental_date rentalDate, r.return_date returnDate from film f "
				+"inner join inventory i on f.film_id = i.film_id "
				+"inner join rental r on i.inventory_id = r.inventory_id "
				+"inner join customer c on r.customer_id = c.customer_id "
				+"inner join staff sf on r.staff_id = sf.staff_id "
				+"inner join store se on se.store_id = sf.store_id "
				+"where f.title LIKE ? and se.store_id = ? "
				+"order by r.rental_date desc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, "%" + searchWord + "%");
		stmt2.setInt(2, sid);
		stmt2.setInt(3, startRow);
		stmt2.setInt(4, rowPerPage);
	}
	}
	rs2 = stmt2.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs2.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rentalId", rs2.getObject("rentalId"));
		map.put("title", rs2.getObject("title"));
		map.put("inventoryId", rs2.getObject("inventoryId"));
		map.put("fName", rs2.getObject("fName"));
		map.put("lName", rs2.getObject("lName"));
		map.put("customerId", rs2.getObject("customerId"));
		map.put("storeId", rs2.getObject("storeId"));
		map.put("rentalDate", rs2.getObject("rentalDate"));
		map.put("returnDate", rs2.getObject("returnDate"));
		list.add(map);
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Rental List</h1>
	<form action="/sakila/d0325/rentalList.jsp">
		Store : 
		<select name="storeId">
			<option value="0">전체</option>
			<option value="1">1지점</option>
			<option value="2">2지점</option>
		</select>
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	
	
	<table border="1">
		<tr>
			<th>rentalId</th>
			<th>filmTitle</th>
			<th>inventoryId</th>
			<th>name(customerId)</th><!-- name = firstName + lastName -->
			<th>rentalDate</th>
			<th>returnDate</th>
		</tr>
		<%
			for(HashMap<String, Object> c : list) { // for(HashMap<String, Object> map : list))
		%>
				<tr>
					<td><%=c.get("rentalId")%></td>
					<td><%=c.get("title")%></td>
					<td><%=c.get("inventoryId")%></td>
					<td><%=c.get("fName")%> <%=c.get("lName")%>(<%=c.get("customerId")%>)</td>
					<td><%=c.get("rentalDate")%></td>
					<td><%=c.get("returnDate")%></td>
				</tr>
		<%
			}
		%>
	</table>
	<%=currentPage %>/ <%=lastPage %> <br>
	<%
		if(currentPage > 1) {
	%>
		<a href="/sakila/d0325/rentalList.jsp?currentPage=1&searchWord=<%=searchWord%>&storeId=<%=sid%>">[처음]</a>
	<%
		}
	%>
	<%
		if(currentPage >= 11) {
	%>
		<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=currentPage-10%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[이전10]</a>
	<%
		}
	%>
	<%
		if(currentPage > 1) {
	%>
			<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[이전]</a>
	<%	
		}
	%>
	<%
		if(endPage > lastPage) {
			endPage = lastPage;
		}
			for(int i = startPage ; i <= endPage ; i++){
				if(i == currentPage) {
	%>
					[<%=currentPage%>]
	<%
				}	else {
	%>
			<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=i%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[<%=i%>]</a>
	<%
				}
			}
	%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[다음]</a>
	<%	
		}
	%>
	<%
		if(currentPage <= lastPage-10) {
	%>
		<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=currentPage+10%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[다음10]</a>
	<%
		} 
	%>
	<%
		if(currentPage < lastPage) {
	%>
		<a href="/sakila/d0325/rentalList.jsp?currentPage=<%=lastPage%>&searchWord=<%=searchWord%>&storeId=<%=sid%>">[마지막]</a>
	<%
		}
	%>
</body>
</html>
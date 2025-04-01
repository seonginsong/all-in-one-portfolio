<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
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

	//[1], [2]...를 위한 시작페이지, 끝페이지
	int startPage = (((currentPage - 1) / rowPerPage) * rowPerPage) + 1;
	int endPage = startPage + rowPerPage - 1;
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	
	//총페이지수 - 평점은 선택, 전부(rating을 넣음으로 구분x), 검색어 유무
	String sql1 = "select count(*) cnt from customer c "
					+"left join address a on c.address_id = a.address_id "
					+"left join city ci on a.city_id = ci.city_id "
					+"left join country co on ci.country_id = co.country_id "
					+"left join staff s on s.store_id = c.store_id";
			
	if(searchWord == "") {
		stmt1 = conn.prepareStatement(sql1);
	}	else {
		sql1 += " where c.first_name like ? or c.last_name like ?";
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, "%" + searchWord + "%");
		stmt1.setString(2, "%" + searchWord + "%");
	}
	rs1 = stmt1.executeQuery();
	System.out.println(sql1);
	
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
			
	// 리스트 목록
	PreparedStatement stmt2 = null;
	ResultSet rs2 = null;
	
	String sql2 = "select c.customer_id ID, concat(c.first_name, ' ', c.last_name) as name "
					+", a.address address, a.postal_code zipCode, a.phone phone, ci.city city, co.country country"
					+", case when c.active = 1 then 'active' else ' ' end notes, s.staff_id SID "
					+"from customer c left join address a on c.address_id = a.address_id "
					+"left join city ci on a.city_id = ci.city_id "
					+"left join country co on ci.country_id = co.country_id "
					+"left join staff s on s.store_id = c.store_id";
	
	if(searchWord == "") {
		sql2 += " order by ID asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, startRow);
		stmt2.setInt(2, rowPerPage);
	} else {
		sql2 += " where c.first_name like ? or c.last_name like ? order by ID asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, "%" + searchWord + "%");
		stmt2.setString(2, "%" + searchWord + "%");
		stmt2.setInt(3, startRow);
		stmt2.setInt(4, rowPerPage);
	}
	
	rs2 = stmt2.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs2.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ID", rs2.getObject("ID"));
		map.put("name", rs2.getObject("name"));
		map.put("address", rs2.getObject("address"));
		map.put("zipCode", rs2.getObject("zipCode"));
		map.put("phone", rs2.getObject("phone"));
		map.put("city", rs2.getObject("city"));
		map.put("country", rs2.getObject("country"));
		map.put("notes", rs2.getObject("notes"));
		map.put("SID", rs2.getObject("SID"));
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
	<h1>Inventory List</h1>
	<form action="/sakila/d0401/viewCustomerList.jsp">
		name : 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>name</th>
			<th>address</th>
			<th>zip_code</th>
			<th>phone</th>
			<th>city</th>
			<th>country</th>
			<th>notes</th>
			<th>SID</th>
		</tr>
		<%
			for(HashMap<String, Object> i : list) {
		%>
				<tr>
					<td><%=i.get("ID")%></td>
					<td><%=i.get("name")%></td>
					<td><%=i.get("address")%></td>
					<td><%=i.get("zipCode")%></td>
					<td><%=i.get("phone")%></td>
					<td><%=i.get("city")%></td>
					<td><%=i.get("country")%></td>
					<td><%=i.get("notes")%></td>
					<td><%=i.get("SID")%></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<%=currentPage %>/ <%=lastPage %> <br>
	<%
		if(currentPage > 1) {
	%>
		<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=1&searchWord=<%=searchWord%>">[처음]</a>
	<%
		}
	%>
	<%
		if(currentPage >= 11) {
	%>
		<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=currentPage-10%>&searchWord=<%=searchWord%>">[이전10]</a>
	<%
		}
	%>
	<%
		if(currentPage > 1) {
	%>
			<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">[이전]</a>
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
			<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=i%>&searchWord=<%=searchWord%>">[<%=i%>]</a>
	<%
				}
			}
	%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">[다음]</a>
	<%	
		}
	%>
	<%
		if(currentPage <= lastPage-10) {
	%>
		<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=currentPage+10%>&searchWord=<%=searchWord%>">[다음10]</a>
	<%
		} 
	%>
	<%
		if(currentPage < lastPage) {
	%>
		<a href="/sakila/d0401/viewCustomerList.jsp?currentPage=<%=lastPage%>&searchWord=<%=searchWord%>">[마지막]</a>
	<%
		}
	%>
</body>
</html>
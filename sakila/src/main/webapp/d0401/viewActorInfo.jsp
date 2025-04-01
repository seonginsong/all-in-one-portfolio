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
	String sql1 = "select count(distinct a.actor_id) cnt from actor a "
					+"left join film_actor fa on a.actor_id = fa.actor_id "
					+"left join film_category fc on fa.film_id = fc.film_id "
					+"left join category c on fc.category_id = c.category_id";
			
	if(searchWord == "") {
		stmt1 = conn.prepareStatement(sql1);
	}	else {
		sql1 += " where a.first_name like ? or a.last_name like ?";
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
	
	String sql2 = "select a.actor_id actorId, a.first_name firstName, a.last_name lastName, group_concat(distinct "
					+"concat(c.name, ': ', (select group_concat(f.title order by f.title asc separator ', ') "
					+"from ((film f join film_category fc on ((f.film_id = fc.film_id))) "
					+"join film_actor fa on ((f.film_id = fa.film_id)))"
					+"where ((fc.category_id = c.category_id) and (fa.actor_id = a.actor_id))))"
					+"order by c.name asc separator '; ') as filmInfo "
					+"from (((actor a left join film_actor fa on ((a.actor_id = fa.actor_id)))"
					+"left join film_category fc on((fa.film_id = fc.film_id)))"
					+"left join category c on((fc.category_id = c.category_id)))";
	
	if(searchWord == "") {
		sql2 += " group by a.actor_id, a.first_name, a.last_name order by a.actor_id asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, startRow);
		stmt2.setInt(2, rowPerPage);
	} else {
		sql2 += " where a.first_name like ? or a.last_name like ? group by a.actor_id, a.first_name, a.last_name order by a.actor_id asc limit ?, ?";
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
		map.put("actorId", rs2.getObject("actorId"));
		map.put("firstName", rs2.getObject("firstName"));
		map.put("lastName", rs2.getObject("lastName"));
		map.put("filmInfo", rs2.getObject("filmInfo"));
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
	<form action="/sakila/d0401/viewActorInfo.jsp">
		name : 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	<table border="1">
		<tr>
			<th>actor_id</th>
			<th>first_name</th>
			<th>last_name</th>
			<th>film_info</th>
		</tr>
		<%
			for(HashMap<String, Object> i : list) {
		%>
				<tr>
					<td><%=i.get("actorId")%></td>
					<td><%=i.get("firstName")%></td>
					<td><%=i.get("lastName")%></td>
					<td><%=i.get("filmInfo")%></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<%=currentPage %>/ <%=lastPage %> <br>
	<%
		if(currentPage > 1) {
	%>
		<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=1&searchWord=<%=searchWord%>">[처음]</a>
	<%
		}
	%>
	<%
		if(currentPage >= 11) {
	%>
		<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=currentPage-10%>&searchWord=<%=searchWord%>">[이전10]</a>
	<%
		}
	%>
	<%
		if(currentPage > 1) {
	%>
			<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">[이전]</a>
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
			<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=i%>&searchWord=<%=searchWord%>">[<%=i%>]</a>
	<%
				}
			}
	%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">[다음]</a>
	<%	
		}
	%>
	<%
		if(currentPage <= lastPage-10) {
	%>
		<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=currentPage+10%>&searchWord=<%=searchWord%>">[다음10]</a>
	<%
		} 
	%>
	<%
		if(currentPage < lastPage) {
	%>
		<a href="/sakila/d0401/viewActorInfo.jsp?currentPage=<%=lastPage%>&searchWord=<%=searchWord%>">[마지막]</a>
	<%
		}
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%
	//가져온 제목
	String title = request.getParameter("title");
	System.out.println(title);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	//영화 상세정보
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	
	String sql1 = "select f.film_id filmId, f.title title, f.description description"
					+", f.release_year releaseYear, l.name language"
					+", f.rental_duration rentalDuration, f.rental_rate rentalRate"
					+", f.length length, f.replacement_cost replacementCost, f.rating rating"
					+", f.special_features specialFeatures, f.last_update lastUpdate "
					+"from film f inner join language l on f.language_id = l.language_id "
					+"where f.title = ?";
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setString(1, title);
	rs1 = stmt1.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs1.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("filmId", rs1.getObject("filmId"));
		map.put("title", rs1.getObject("title"));
		map.put("description", rs1.getObject("description"));
		map.put("language", rs1.getObject("language"));
		map.put("rentalDuration", rs1.getObject("rentalDuration"));
		map.put("rentalRate", rs1.getObject("rentalRate"));
		map.put("length", rs1.getObject("length"));
		map.put("replacementCost", rs1.getObject("replacementCost"));
		map.put("rating", rs1.getObject("rating"));
		map.put("specialFeatures", rs1.getObject("specialFeatures"));
		map.put("lastUpdate", rs1.getObject("lastUpdate"));
		list.add(map);
	}
	
	// 출연자 목록
	PreparedStatement stmt2 = null;
	ResultSet rs2 = null;
	
	String sql2 = "select ac.actor_id actorId, ac.first_name acFirstName, ac.last_name acLastName, f.title title from film f "
					+"inner join film_actor fa on f.film_id = fa.film_id "
					+"inner join actor ac on fa.actor_id = ac.actor_id where f.title = ?";
	stmt2 = conn.prepareStatement(sql2);
	stmt2.setString(1, title);
	rs2 = stmt2.executeQuery();
	
	ArrayList<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
	
	while(rs2.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("acFirstName", rs2.getObject("acFirstName"));
		map.put("acLastName", rs2.getObject("acLastName"));
		map.put("title", rs2.getObject("title"));
		map.put("actorId", rs2.getObject("actorId"));
		list2.add(map);
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- 영화 상세정보 -->
	<h1>Film One</h1>
	<table border="1">
		<%
			for(HashMap<String, Object> f : list) { // for(HashMap<String, Object> map : list))
		%>
				<tr>
					<th>filmId</th>
					<td><%=f.get("filmId")%></td>
				</tr>
				<tr>
					<th>filmTitle</th>
					<td><%=f.get("title")%></td>
				</tr>
				<tr>
					<th>description</th>
					<td><%=f.get("description")%></td>
				</tr>
				<tr>
					<th>language</th>
					<td><%=f.get("language")%></td>
				</tr>
				<tr>
					<th>rentalDuration</th>
					<td><%=f.get("rentalDuration")%></td>
				</tr>
				<tr>
					<th>rentalRate</th>
					<td><%=f.get("rentalRate")%></td>
				</tr>		
				<tr>
					<th>length</th>
					<td><%=f.get("length")%></td>
				</tr>		
				<tr>
					<th>replacementCost</th>
					<td><%=f.get("replacementCost")%></td>
				</tr>		
				<tr>
					<th>rating</th>
					<td><%=f.get("rating")%></td>
				</tr>				
				<tr>
					<th>specialFeatures</th>
					<td><%=f.get("specialFeatures")%></td>
				</tr>		
				<tr>
					<th>lastUpdate</th>
					<td><%=f.get("lastUpdate")%></td>
				</tr>						
		<%
			}
		%>
	</table>
	<hr>
	<!-- 출연자 목록 -->
	<h1>출연자 목록</h1>
	<table border="1">
		<tr>
			<th>출연자 ID</th>
			<th>출연자 목록</th>
		</tr>
		<%
			for(HashMap<String, Object> a : list2) {
		%>
				<tr>
					<td><%=a.get("actorId")%></td>
					<td><a href="/sakila/d0326/actorOne.jsp?actorId=<%=a.get("actorId")%>"><%=a.get("acFirstName")%>&nbsp;<%=a.get("acLastName")%></a></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>
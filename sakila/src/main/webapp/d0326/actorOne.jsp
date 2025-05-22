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
	//actorId 가져오기
	int actorId = Integer.parseInt(request.getParameter("actorId"));
	System.out.println(actorId);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");

	// 출연자 상세정보 (출연작 목록)
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	
	String sql1 = "select ac.actor_id actorId, ac.first_name acFirstName, ac.last_name acLastName, f.title title from film f "
					+"inner join film_actor fa on f.film_id = fa.film_id "
					+"inner join actor ac on fa.actor_id = ac.actor_id where ac.actor_id = ?";
	stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, actorId);
	rs1 = stmt1.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs1.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("actorId", rs1.getObject("actorId"));
		map.put("acFirstName", rs1.getObject("acFirstName"));
		map.put("acLastName", rs1.getObject("acLastName"));
		map.put("title", rs1.getObject("title"));
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
	<h1>출연자 상세정보</h1>
	<table border="1">
		<%
			HashMap<String, Object> fa = list.get(0);
		%>
			<tr>
				<th>actor id : <%=fa.get("actorId")%>&nbsp;
				<%=fa.get("acFirstName")%>&nbsp;<%=fa.get("acLastName")%>의 출연작</th>
			</tr>
		<%
			for(HashMap<String, Object> a : list) {
		%>
				<tr>
					<td><a href="/sakila/d0326/filmOne.jsp?title=<%=a.get("title")%>"><%=a.get("title")%></a></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>
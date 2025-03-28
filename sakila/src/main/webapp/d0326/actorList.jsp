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
	
	//[1], [2]...를 위한 시작페이지, 끝페이지
	int startPage = (((currentPage - 1) / rowPerPage) * rowPerPage) + 1;
	int endPage = startPage + rowPerPage - 1;
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "java1234");
	
	PreparedStatement stmt1 = null;
	ResultSet rs1 = null;
	//총 페이지 수 - 검색어만 존재
	String sql1 = "select count(*) cnt from ("
					+"select ac.actor_id from actor ac "
					+"group by ac.actor_id) sq";
	
	if(searchWord == "") {
		stmt1 = conn.prepareStatement(sql1);
	} else {
		sql1 = "select count(*) cnt from ("
				+"select ac.actor_id from actor ac "
				+"where ac.first_name like ? or ac.last_name like ? "
				+"group by ac.actor_id) sq";
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, "%" + searchWord + "%");
		stmt1.setString(2, "%" + searchWord + "%");
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
	
	// 리스트 목록
	PreparedStatement stmt2 = null;
	ResultSet rs2 = null;
	
	String sql2 = "select ac.actor_id, concat(ac.first_name, ' ', ac.last_name) name"
					+", count(fa.actor_id) fcnt from actor ac "
					+"inner join film_actor fa on ac.actor_id = fa.actor_id ";
	
	if(searchWord == "") {
		sql2 += " group by ac.actor_id, ac.first_name, ac.last_name limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, startRow);
		stmt2.setInt(2, rowPerPage);
	} else {
		sql2 += "where (ac.first_name like ? or ac.last_name like ?) "
				+"group by ac.actor_id, ac.first_name, ac.last_name limit ?, ?";
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
		map.put("name", rs2.getObject("name"));
		map.put("fcnt", rs2.getObject("fcnt"));
		map.put("actorId", rs2.getObject("ac.actor_id"));
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
	<h1>Actor List</h1>
	<form action="/sakila/d0326/actorList.jsp">
		name : 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	<table border="1">
		<tr>
			<th>actorId</th>
			<th>actorName</th>
			<th>filmCount</th>
		</tr>
		<%
			for(HashMap<String, Object> f : list) {
		%>
				<tr>
					<td><%=f.get("actorId")%></td>
					<td><a href="/sakila/d0326/actorOne.jsp?actorId=<%=f.get("actorId")%>"><%=f.get("name")%></a></td>
					<td><%=f.get("fcnt")%></td>
				</tr>
		<%
			}
		%>
	</table>
	<%=currentPage %>/ <%=lastPage %> <br>
	<%
		if(currentPage > 1) {
	%>
		<a href="/sakila/d0326/actorList.jsp?currentPage=1&searchWord=<%=searchWord%>">[처음]</a>
	<%
		}
	%>
	<%
		if(currentPage >= 11) {
	%>
		<a href="/sakila/d0326/actorList.jsp?currentPage=<%=currentPage-10%>&searchWord=<%=searchWord%>">[이전10]</a>
	<%
		}
	%>
	<%
		if(currentPage > 1) {
	%>
			<a href="/sakila/d0326/actorList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">[이전]</a>
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
			<a href="/sakila/d0326/actorList.jsp?currentPage=<%=i%>&searchWord=<%=searchWord%>">[<%=i%>]</a>
	<%
				}
			}
	%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/sakila/d0326/actorList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">[다음]</a>
	<%	
		}
	%>
	<%
		if(currentPage <= lastPage-10) {
	%>
		<a href="/sakila/d0326/actorList.jsp?currentPage=<%=currentPage+10%>&searchWord=<%=searchWord%>">[다음10]</a>
	<%
		} 
	%>
	<%
		if(currentPage < lastPage) {
	%>
		<a href="/sakila/d0326/actorList.jsp?currentPage=<%=lastPage%>&searchWord=<%=searchWord%>">[마지막]</a>
	<%
		}
	%>
</body>
</html>
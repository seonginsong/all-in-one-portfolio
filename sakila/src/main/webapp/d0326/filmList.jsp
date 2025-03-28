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
	//평점
	String searchRating = request.getParameter("searchRating");
	if(searchRating == null) {
		searchRating = "%";
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
	String sql1 = "select count(*) cnt from film where rating like ?";
	
	if(searchWord == "") {
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, searchRating);
	} else {
		sql1 += " and title like ?";
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, searchRating);
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
	
	String sql2 = "select f.film_id filmId, f.title title, l.name language, f.rating rating from film f inner join language l on f.language_id = l.language_id where rating like ?";
	if(searchWord == "") {
		sql2 += " order by title asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, searchRating);
		stmt2.setInt(2, startRow);
		stmt2.setInt(3, rowPerPage);
	} else {
		sql2 += " and title like ?" + " order by title asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, searchRating);
		stmt2.setString(2, "%" + searchWord + "%");
		stmt2.setInt(3, startRow);
		stmt2.setInt(4, rowPerPage);
	}
	
	rs2 = stmt2.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs2.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("filmId", rs2.getObject("filmId"));
		map.put("title", rs2.getObject("title"));
		map.put("language", rs2.getObject("language"));
		map.put("rating", rs2.getObject("rating"));
		list.add(map);
	}
	
	
	System.out.println(sql2);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Film List</h1>
	<form action="/sakila/d0326/filmList.jsp">
		Rating : 
		<select name="searchRating">
			<option value="%">전체</option>
			<option value="G">G</option>
			<option value="PG">PG</option>
			<option value="PG-13">PG-13</option>
			<option value="R">R</option>
			<option value="NC-17">NC-17</option>
		</select>
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
	
	
	<table border="1">
		<tr>
			<th>filmId</th>
			<th>filmTitle</th>
			<th>language</th>
			<th>rating</th>
		</tr>
		<%
			for(HashMap<String, Object> f : list) {
		%>
				<tr>
					<td><%=f.get("filmId")%></td>
					<td><a href="/sakila/d0326/filmOne.jsp?title=<%=f.get("title")%>"><%=f.get("title")%></a></td>
					<td><%=f.get("language")%></td>
					<td><%=f.get("rating")%></td>
				</tr>
		<%
			}
		%>
	</table>
	<%=currentPage %>/ <%=lastPage %> <br>
	<%
		if(currentPage > 1) {
	%>
		<a href="/sakila/d0326/filmList.jsp?currentPage=1&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[처음]</a>
	<%
		}
	%>
	<%
		if(currentPage >= 11) {
	%>
		<a href="/sakila/d0326/filmList.jsp?currentPage=<%=currentPage-10%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[이전10]</a>
	<%
		}
	%>
	<%
		if(currentPage > 1) {
	%>
			<a href="/sakila/d0326/filmList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[이전]</a>
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
			<a href="/sakila/d0326/filmList.jsp?currentPage=<%=i%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[<%=i%>]</a>
	<%
				}
			}
	%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/sakila/d0326/filmList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[다음]</a>
	<%	
		}
	%>
	<%
		if(currentPage <= lastPage-10) {
	%>
		<a href="/sakila/d0326/filmList.jsp?currentPage=<%=currentPage+10%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[다음10]</a>
	<%
		} 
	%>
	<%
		if(currentPage < lastPage) {
	%>
		<a href="/sakila/d0326/filmList.jsp?currentPage=<%=lastPage%>&searchWord=<%=searchWord%>&searchRating=<%=searchRating%>">[마지막]</a>
	<%
		}
	%>
</body>
</html>
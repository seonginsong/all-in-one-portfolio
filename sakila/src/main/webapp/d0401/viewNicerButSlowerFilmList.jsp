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
	String sql1 = "select count(*) cnt from (select i.inventory_id, f.title "
					+"from inventory i inner join film f on i.film_id = f.film_id) t1 "
					+"left join (select inventory_id, rental_date"
					+", case when return_date is null then '대여불가' "
					+"else '대여가능' end isRental from rental "
					+"where (inventory_id, rental_date) in "
					+"(select inventory_id, max(rental_date) from rental group by inventory_id) "
					+"order by inventory_id asc) t2 on t1.inventory_id = t2.inventory_id";
			
	if(searchWord == "") {
		stmt1 = conn.prepareStatement(sql1);
	}	else {
		sql1 += " where t1.title like ?";
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, "%" + searchWord + "%");
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
	
	String sql2 = "select t1.inventory_id inventoryId, t1.title title, t2.isRental isRental "
					+"from (select i.inventory_id, f.title "
					+"from inventory i inner join film f on i.film_id = f.film_id) t1 "
					+"left join (select inventory_id, rental_date"
					+", case when return_date is null then '반납하기' "
					+"else '대여하기' end isRental from rental "
					+"where (inventory_id, rental_date) in "
					+"(select inventory_id, max(rental_date) from rental group by inventory_id) "
					+"order by inventory_id asc) t2 on t1.inventory_id = t2.inventory_id";
	
	if(searchWord == "") {
		sql2 += " order by t1.title asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, startRow);
		stmt2.setInt(2, rowPerPage);
	} else {
		sql2 += " where t1.title like ? order by t1.title asc limit ?, ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, "%" + searchWord + "%");
		stmt2.setInt(2, startRow);
		stmt2.setInt(3, rowPerPage);
	}
	
	rs2 = stmt2.executeQuery();
	
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	
	while(rs2.next()) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("inventoryId", rs2.getObject("inventoryId"));
		map.put("title", rs2.getObject("title"));
		map.put("isRental", rs2.getObject("isRental"));
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

</body>
</html>
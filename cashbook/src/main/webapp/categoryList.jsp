<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}

	
	String searchWord = "";
	if(request.getParameter("searchWord") != null) {
		searchWord = request.getParameter("searchWord");
	}
	
	String kind = "";
	String strKind = request.getParameter("kind");
	if(strKind != null && !request.getParameter("kind").equals("전체")) {
		kind = request.getParameter("kind");
	}
	// 페이징
	int currentPage = 1;
	int rowPerPage = 10;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	Paging p = new Paging();
	p.setCurrentPage(currentPage);
	p.setRowPerPage(rowPerPage);
		
	CategoryDao categoryDao = new CategoryDao();
	
	int totalRow = categoryDao.getTotalCategory(searchWord, kind);
	int lastPage = p.getLastPage(totalRow);

	ArrayList<Category> list = categoryDao.selectCategoryList(p, searchWord, kind);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook/css/style.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>Category List</h1>
		<%
			if(request.getParameter("msg") != null) {
		%>		
			<div><%=Integer.parseInt(request.getParameter("cgno"))%>번은 Cash가 존재해서 삭제가 불가능합니다.</div>
		<%
			}
		%>
		<table border="1">
			<tr>
				<th>category no</th>
				<th>kind</th>
				<th>title</th>
				<th>createdate</th>
				<th>title 변경하기</th>
				<th>삭제하기</th>
			</tr>
			<%
				for(Category c : list) {
			%>
					<tr>
						<td><%=c.getCategoryNo()%></td>
						<td><%=c.getKind()%></td>
						<td><%=c.getTitle()%></td>
						<td><%=c.getCreatedate()%></td>
						<td><button type="button" onclick="location.href='/cashbook/updateCategoryTitleForm.jsp?category_no=<%=c.getCategoryNo()%>'">변경하기</button></td>
						<td><button type="button" class="delete-btn" onclick="location.href='/cashbook/deleteCategory.jsp?category_no=<%=c.getCategoryNo()%>'">삭제하기</button>
						</td>
					</tr>
			<%	
				}
			%>
		</table>
		<form action="/cashbook/categoryList.jsp" class="search-bar">
			<select name="kind">
				<option value="전체" <%= kind.equals("") ? "selected" : "" %>>전체</option>
				<option value="수입" <%= kind.equals("수입") ? "selected" : "" %>>수입</option>
				<option value="지출" <%= kind.equals("지출") ? "selected" : "" %>>지출</option>
			</select>
			<input type="text" name="searchWord" value="<%=searchWord%>">
			<button type="submit">검색</button>
		</form>
		<div style="text-align: center;">
			<% 
				if(currentPage > 1) { 
			%>
				<a href="/cashbook/categoryList.jsp?currentPage=<%=currentPage - 1%>&searchWord=<%=searchWord%>">[이전]</a>
			<%
				} 
			%>
			<%=currentPage%>/<%=lastPage%>
			<%
				if(currentPage < lastPage) { 
			%>
				<a href="/cashbook/categoryList.jsp?currentPage=<%=currentPage + 1%>&searchWord=<%=searchWord%>">[다음]</a>
			<%
				} 
			%>
		</div>
</body>
</html>
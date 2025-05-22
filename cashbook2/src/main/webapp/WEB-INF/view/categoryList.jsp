<%@page import="java.util.ArrayList"%>
<%@page import="dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook2/css/style.css">
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
				for(Category c : (ArrayList<Category>)request.getAttribute("list")) {
			%>
					<tr>
						<td><%=c.getCategoryNo()%></td>
						<td><%=c.getKind()%></td>
						<td><%=c.getTitle()%></td>
						<td><%=c.getCreatedate()%></td>
						<td><button type="button" onclick="location.href='<%=request.getContextPath()%>/updateCategoryTitle?categoryNo=<%=c.getCategoryNo()%>'">변경하기</button></td>
						<td><button type="button" class="delete-btn" onclick="location.href='<%=request.getContextPath()%>/deleteCategory?categoryNo=<%=c.getCategoryNo()%>'">삭제하기</button>
						</td>
					</tr>
			<%	
				}
			%>
		</table>
		<form action="<%=request.getContextPath()%>/categoryList" class="search-bar">
			<select name="kind">
				<option value="전체" <%= (String)request.getAttribute("kind")%>.equals("") ? "selected" : "" %>전체</option>
				<option value="수입" <%= (String)request.getAttribute("kind")%>.equals("수입") ? "selected" : "" %>수입</option>
				<option value="지출" <%= (String)request.getAttribute("kind")%>.equals("지출") ? "selected" : "" %>지출</option>
			</select>
			<input type="text" name="searchWord" value="<%=(String)request.getAttribute("searchWord")%>">
			<button type="submit">검색</button>
		</form>
		<div style="text-align: center;">
			<% 
				if((Integer)request.getAttribute("currentPage") > 1) { 
			%>
				<a href="<%=request.getContextPath()%>/categoryList?currentPage=<%=(Integer)request.getAttribute("currentPage") - 1%>&searchWord=<%=(String)request.getAttribute("searchWord")%>">[이전]</a>
			<%
				} 
			%>
			<%=(Integer)request.getAttribute("currentPage")%>/<%=(Integer)request.getAttribute("lastPage")%>
			<%
				if((Integer)request.getAttribute("currentPage") < (Integer)request.getAttribute("lastPage")) { 
			%>
				<a href="<%=request.getContextPath()%>/categoryList?currentPage=<%=(Integer)request.getAttribute("currentPage") + 1%>&searchWord=<%=(String)request.getAttribute("searchWord")%>">[다음]</a>
			<%
				} 
			%>
		</div>
</body>
</html>
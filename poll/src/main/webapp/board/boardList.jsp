<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
	String searchWord = "";
	if(request.getParameter("searchWord") != null) {
		searchWord = request.getParameter("searchWord");
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
		
	BoardDao boardDao = new BoardDao();
	
	int totalRow = boardDao.getTotalBoard(searchWord);
	int lastPage = p.getLastPage(totalRow);

	ArrayList<Board> list = boardDao.selectBoardList(p, searchWord);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1>BoardList</h1>
	<!-- nav.jsp 인클루드 -->
	<div>
		<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<!-- boardListTable 출력 -->
	<form action="/poll/board/boardList.jsp">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<td>num</td>
				<td>subject</td>
				<td>name</td>
			</tr>
		</thead>
		<tbody>
			<%
				for(Board b : list) {
			%>
				<tr>
					<td><%=b.getNum()%></td>
					<td>
						<%
							for(int i=0; i<=b.getDepth(); i++) {
						%>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<%
							}
						%>
						<%
							if(b.getDepth()>0) {
						%>
								└						
						<%		
							}
						%>
					<a href="/poll/board/boardOne.jsp?num=<%=b.getNum()%>"><%=b.getSubject()%></a></td>
					<td><%=b.getName()%></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<!-- 페이징 -->
	<%
		if(currentPage > 1) {
	%>
			<a href="/poll/board/boardList.jsp?currentPage=<%=currentPage - 1%>&searchWord=<%=searchWord%>">[이전]</a>
	<%
		}
	%>
			<%=currentPage%>/<%=lastPage%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/poll/board/boardList.jsp?currentPage=<%=currentPage + 1%>&searchWord=<%=searchWord%>">[다음]</a>
	<%
		}
	%>
	<input type="text" name="searchWord" value="<%=searchWord%>" placeholder="subject"><button type="submit">검색하기</button>
	</form>
</body>
</html>
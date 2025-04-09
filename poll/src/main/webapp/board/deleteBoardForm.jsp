<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dto.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	
	BoardDao boardDao = new BoardDao();
	Board b = boardDao.selectBoardOne(num);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<%
		if(request.getParameter("msg") != null) {
	%>
		<div><%=request.getParameter("msg")%></div>
	<%
		}
	%>
</head>
<body>

<form action="/poll/board/deleteBoardAction.jsp">
	<table class="table table-striped table-bordered">
		<tr>
			<th>num</th>
			<th>name</th>
			<th>subject</th>
			<th>content</th>
		</tr>
		<tr>
			<td><%=b.getNum()%></td>
			<td><%=b.getName()%></td>
			<td><%=b.getSubject()%></td>
			<td><%=b.getContent()%></td>
		</tr>
	<input type="hidden" name="num" value="<%=num%>">
	</table>
	패스워드:<input type="password" name="pass"><button type="submit">삭제하기</button>
</form>
</body>
</html>
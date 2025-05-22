<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dto.*" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	
	// value값을 설정하기 위해
	
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
</head>
<body class="container">
	<!-- nav.jsp 인클루드 -->
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	
	<h1>글수정</h1>
	<form method="post" action="/poll/board/updateBoardAction.jsp">
		<table class="table table-striped table-bordered">
			<input type="hidden" name="num" value="<%=b.getNum()%>">
			<tr>
				<td>name</td>
				<td><input type="text" name="name" value="<%=b.getName()%>"></td>
			</tr>
			<tr>
				<td>subject</td>
				<td><input type="text" name="subject" value="<%=b.getSubject()%>"></td>
			</tr>
			<tr>
				<td>content</td>
				<td><textarea name="content" rows="5" cols="50"><%=b.getContent()%></textarea></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="pass"></td>
			</tr>
		</table>
		<button type="submit">글쓰기</button>
	</form>
</body>
</html>
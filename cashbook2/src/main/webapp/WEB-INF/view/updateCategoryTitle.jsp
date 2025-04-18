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
	<h1>Category Title 수정</h1>
		<%
			if(request.getParameter("umsg") != null) {
		%>		
			<div>Title이 이미 존재해서 변경이 불가능합니다.</div>
		<%
			}
		%>
		<%
			if(request.getParameter("msg") != null) {
		%>		
			<div>입력값이 없습니다.</div>
		<%
			}
		%>
	<form action="<%=request.getContextPath()%>/updateCategoryTitle" method="post">
		<table border="1">
			<tr>
				<th>현재 title</th>
				<th>변경할 title</th>
			</tr>
			<tr>
				<td>
				<input type="hidden" name="categoryNo" value='<%=(Integer)request.getAttribute("cgno")%>'>
				<input type="text" name="previousTitle" value="<%=(String)request.getAttribute("title")%>" readonly>
				<td><input type="text" name="title"></td>
			</tr>
		</table>
		<div align="center"><button type="submit">변경하기</button></div>
	</form>
</body>
</html>
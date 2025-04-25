<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/cashbook2/css/style.css">
		<title>영수증 올리기</title>
	</head>
<body>
	<%	
		if(request.getParameter("msg") != null) { // insertImageAction png,jpg파일이 아닐 경우에만 msg값이 넘어옴
	%>
		<div>png나 jpg파일이 아닙니다.</div>
	<%
		}
	%>
	<div>
		<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<form action="<%=request.getContextPath()%>/insertReceit" method="post" enctype="multipart/form-data">
		<input type="hidden" name="cashNo" value="<%=request.getAttribute("cashNo")%>">
		<div>영수증 : <input type="file" name="receitFile" /></div>
		<button type="submit">이미지 등록</button>
    </form>
</body>
</html>
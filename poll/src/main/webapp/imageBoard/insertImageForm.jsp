<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>이미지 올리기</title>
	</head>
<body>
	<%	
		if(request.getParameter("msg") != null) { // insertImageAction png파일이 아닐 경우에만 msg값이 넘어옴
	%>
		<div><%=request.getParameter("msg")%></div>
	<%
		}
	%>
	<div>
		<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<form action="/poll/imageBoard/insertImageAction.jsp" method="post" enctype="multipart/form-data">
		<div>메모 : <input type="text" name="memo" /></div>
		<div>이미지 : <input type="file" name="imageFile" /></div>
		<button type="submit">이미지 등록</button>
    </form>
</body>
</html>
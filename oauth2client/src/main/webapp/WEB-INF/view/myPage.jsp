<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>My Page</h1>
	<hr>
	loginUsername : ${loginUsername}<br>
	loginRole : ${loginRole }
	<c:if test="${not empty profileImage}">
		<img src="${profileImage}" alt="프로필 이미지" style="width:80px;height:80px;border-radius:50%;object-fit:cover;margin-bottom:10px;"/>
	</c:if>
</body>
</html>
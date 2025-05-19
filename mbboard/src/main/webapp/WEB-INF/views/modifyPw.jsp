<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${modifyFail}">
	    <script>
	        alert("현재 비밀번호가 일치하지 않습니다.");
	    </script>
	</c:if>
	<c:if test="${pwNotCorrect}">
	    <script>
	        alert("변경할 비밀번호가 일치하지 않습니다.");
	    </script>
	</c:if>
	<c:if test="${samePw}">
	    <script>
	        alert("동일한 비밀번호로는 변경할 수 없습니다.");
	    </script>
	</c:if>
	<h1>비밀번호 변경</h1>
	<form action="modifyPw" method="post">
	id : <input type="text" name="memberId" value="${loginMember.memberId}" readonly><br>
	now pw : <input type="text" name="currentPw">                                    <br>
	next pw : <input type="text" name="newPw">                                       <br>
	next pw2 : <input type="text" name="newPw2">                                     <br>
	<button type="submit">변경하기</button>
	</form>
</body>
</html>
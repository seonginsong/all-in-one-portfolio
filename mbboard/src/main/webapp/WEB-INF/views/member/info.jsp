<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>${loginMember.memberId}님 상세정보</h1>
	<div>
		MEMBER_ROLE : ${loginMember.memberRole}
	</div>
	<div>
		<a href="/modifyPw">비밀번호 수정</a>
	</div>
	<div>
		<c:if test="${loginMember.memberRole.equals('MEMBER')}">
			<a href="/member/memberHome">memberHome으로 돌아가기</a>
		</c:if>
		<c:if test="${loginMember.memberRole.equals('ADMIN')}">
			<a href="/admin/adminHome">adminHome으로 돌아가기</a>
		</c:if>
		<a href="/logout">로그아웃</a>
		<a href="/index">통계</a>
	</div>
</body>
</html>
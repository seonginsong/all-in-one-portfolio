<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<a href="/member/">비밀번호 수정</a>
	</div>
	<div>
		<a href="/member/memberHome">memberHome으로 돌아가기</a>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>rechangeMemberPw</h1>
	<form action="/rechangeMemberPw" method="post">
		<div>
			member Id <input type="text" name="memberId">
		</div>
		<div>
			메일로 받은 pw <input type="password" name="memberPw">
		</div>
		<div>
			변경할 pw <input type="password" name="reMemberPw">
		</div>
		<button type="submit">패스워드 변경</button>
		*10분 안에 비밀번호 변경
	</form>
	<!-- 
		UPDATE member
			SET member_pw = reMemberPw
			WHERE member_pw = memberPw
			AND pwcktime is NOT NULL
			AND TIMESTAMPDIFF(MINUTE,pwcktime,NOW()) < 11
	 -->
</body>
</html>
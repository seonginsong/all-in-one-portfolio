<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
  <link rel="stylesheet" href="/css/whitemono-style.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		$('.change').click(function(){
			const memberId = $(this).data('id'); // 버튼에 있는 memberId 읽기
			$.ajax({
				url: '/updateRole/' + memberId,
				type: 'post',
				success: function(data) {
					alert('변경되었습니다.');
					location.reload(); // 페이지 새로고침으로 반영
				},
				error: function() {
					alert('오류 발생!');
				}
			});
		});
	});
</script>
</head>
<body>
<h1>멤버리스트</h1>
<table border="1">
	<tr>
		<th>Id</th>
		<th>Role</th>
		<th>change</th>
	</tr>
	
	<c:forEach var="m" items="${list}">
		<tr>
			<td>${m.memberId}</td>
			<td>${m.memberRole}</td>
			<td>
				<c:choose>
					<c:when test="${m.memberId == loginMember.memberId}">
						<button type="button" disabled>본인은 변경할 수 없습니다.</button>
					</c:when>
					<c:when test="${m.memberRole == 'ADMIN'}">
						<button type="button" class="change" data-id="${m.memberId}">MEMBER로 변경</button>
					</c:when>
					<c:when test="${m.memberRole == 'MEMBER'}">
						<button type="button" class="change" data-id="${m.memberId}">ADMIN으로 변경</button>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	
</table>
<a href="/admin/adminHome">adminHome으로 돌아가기</a>
<a href="/index">통계</a>

</body>
</html>
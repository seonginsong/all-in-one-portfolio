<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		$('#idckBtn').click(function(){
			if($('#idck').val().trim() != '') {
			$.ajax({
				url: '/isId/'+$('#idck').val()
				, type: 'get'
				, success: function(data) {
					if(data == true) {
						$('#id').val('');
						$('#helpWord').text('사용중인 아이디입니다.').css("color", "red");
					} else {
						$('#helpWord').text('사용 가능한 아이디입니다.').css("color", "green");
						$('#id').val($('#idck').val());
					}
				}
			});
			} else {
				$('#id').val('');
				$('#helpWord').text('검색을 눌러주세요.').css("color", "black");
				alert('아이디를 입력하세요');
			}
		});
		
		$('#btn').click(function(){
			// 입력값 중복검사
			if($('#id').val().trim() != '' && $('#pw').val().trim() != '') {
				// pw == pw2 검사
				if($('#pw').val() == $('#pw2').val()) {
					$('#joinForm').submit();
				} else {
					alert('비밀번호가 일치하지 않습니다');
				}
			} else {
				alert('입력되지 않은 값이 있습니다.');
			};
			
		});	
	});
</script>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form id="joinForm" action="/insertMember" method="post">
		<h2>사용하실 아이디를 입력하세요.</h2>
		<table border = "1">
			<tr>
				<th>아이디 중복 검색</th>
				<td>
					<input type="text" id="idck">
					<button type="button" id="idckBtn">검색</button>
					<span id="helpWord">
						검색을 눌러주세요.
					</span>
				</td>
			</tr>
		</table>
		<hr>
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="id" name="id" readonly>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
				<input type="text" id="pw" name="pw">
				비밀번호 확인 <input type="text" id="pw2" name="pw2">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" id="email" name="email">
				</td>
			</tr>
		</table>
		<button type="button" id="btn">회원가입</button>
	</form>
</body>
</html>
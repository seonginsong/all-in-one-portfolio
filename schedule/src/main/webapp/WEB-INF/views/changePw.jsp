<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>비밀번호 변경</h1>
	<form action="/changePw" name="changePwForm" id="changePwForm" method="post">
		id : ${loginMember.id} <input type="hidden" name="id" value="${loginMember.id}">
		<br>
		nowPw : <input type="password" id="nowPw" name="nowPw">
		<br>
		<span class="ph">
		nextPw : <input type="password" id="nextPw" name="nextPw">
		</span>
		<br>
		nextPwAgain : <input type="password" id="nextPw2" name="nextPw2">
		<br>
		<button type="button" id="changePwBtn">변경하기</button><span id="helpWord"></span>
	</form>
	<script>
		$('#changePwBtn').click(function() {
			$('#helpWord').text('');
			const id = $('input[name="id"]').val();
			const nowPw = $('#nowPw').val();
			const nextPw = $('#nextPw').val();
			const nextPw2 = $('#nextPw2').val();
			const sessionPw = '${loginMember.pw}';
			
			if (nowPw === nextPw) {
			  alert('같은 비밀번호는 사용할 수 없습니다.');
			  return;
			} else if (nextPw !== nextPw2) {
			  alert('변경할 비밀번호가 일치하지 않습니다.');
			  return;
			} else if (nowPw != sessionPw) {
				alert('현재 비밀번호가 일치하지 않습니다.')
			} else {
	
			// 현재 비밀번호 확인 (checkPw)
			$.ajax({
			  url: '/checkPw',
			  type: 'POST',
			  contentType: 'application/json',
			  data: JSON.stringify({ id: id, pw: nextPw }),
			  success: function(data) {
			    if (data == true) {
				// helpWord 초기화
				$('#helpWord').text('');
			      $('#helpWord').text('최근 사용한(5회 이내) 비밀번호로는 변경할 수 없습니다.').css("color", "red");
			    } else {
			      // 다음 비밀번호만 form으로 submit
			      // nextPw를 넘기기 위해 hidden input으로 설정
			      $('#helpWord').text('');
			      $('<input>').attr({
			        type: 'hidden',
			        name: 'pw',
			        value: nextPw
			      }).appendTo('#changePwForm');
			      
			      
			      console.log("submit 직전 - id:", id, ", pw:", nextPw);
			      $('#changePwForm').submit();
			    }
			  }
			});
			}
			});
	</script>
</body>
</html>
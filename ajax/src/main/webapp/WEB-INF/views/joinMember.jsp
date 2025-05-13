<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function() {
		$('#sn1').keyup(function() {
			if($('#sn1').val().length > 5) {
				$('#sn2').focus();
			}
		});
		$('#sn2').keyup(function() {
			if($('#sn2').val().length > 6) {
				$('#idck').focus();
			}
		});
		
		
		$('#sn2').blur(function() {
			// sn1.length == 6 && sn2.length == 7
			// sn1 != isNaN && sn2 != isNaN
			if(isNaN($('#sn1').val()) || isNaN($('#sn2').val())) {
				alert('숫자만 입력');
			} else if($('#sn1').val().length != 6 || $('#sn2').val().length != 7) {
				alert('주민번호 앞 6자리, 뒤 7자리를 입력');
			} else if(!isNaN($('#sn1').val()) && !isNaN($('#sn2').val()) && $('#sn1').val().length == 6 && $('#sn2').val().length == 7) {
			$.ajax({url: "http://localhost:9090/isSn/"+$('#sn1').val()+$('#sn2').val()
					, type: 'get'	
					, success: function(data){
						console.log(data);
						// true / false
						if(data == true) {
							alert('주민번호 인증 성공');
							// 성별을 구해서 값 채우기
							if(Number($('#sn2').val().substr(0, 1)) % 2 == 0) {
								$('#gender').val('여');
							} else {
								$('#gender').val('남');
							}
							// 만나이를 구해서 값채우기
							let today = new Date();
							let tyear = today.getFullYear();
							let tmonth = today.getMonth()+1;
							let tdate = today.getDate();
							
							let year = Number($('#sn1').val().substr(0, 2));
							if(year <= tyear-2000) {
								year += 2000;
							} else {
								year += 1900;
							}
							console.log('year:', year);
							let age = tyear - year - 1;
							if(Number($('#sn1').val().substr(2, 2)) <= tmonth && Number($('#sn1').val().substr(4, 2)) <= tdate) {
								age += 1;
							}
							console.log('age:', age);
							$('#age').val(age);
						} else {
							alert('주민번호 인증 실패');
						}
					}
			
			});
			}
		});
		
		// 내부API서버를 호출 - 비동기 구현 필수X -> 편의상 비동기로 구현
		$('#idckBtn').click(function(){
			// $('#idck').val() 공백이 아니라면
			if($('#idck').val().trim() != '') {
			$.ajax({
				url: '/isId/'+$('#idck').val()
				, type: 'get'
				, success: function(data) {
					if(data == true) {
						alert('이미 사용중인 아이디 입니다.');
					} else {
						alert('사용 가능한 아이디 입니다.');
						$('#id').val($('#idck').val());
					}
				}
			});
			} else {
				alert('아이디를 입력하세요');
			}
		});
		
		$('#btn').click(function(){
			// 입력값 중복검사
			if($('#id').val().trim() != '' && $('#age').val().trim() != '' && $('#gender').val().trim() != '' && $('#pw').val().trim() != '') {
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
		<h2>주민번호확인</h2>
	<hr>
	
	<form id="joinForm" action="/joinMember" method="post">
		<table border="1">
			<tr>
				<th>주민번호</th>
				<td>
					<input type="text" id="sn1"><!-- keyup, length 6, focus sn2 -->
					-
					<input type="text" id="sn2"><!-- blur length 7, snapi호출 true ? gender+age : alert('잘못된 주번') -->
				</td>
			</tr>
		</table>
		
		<hr>
		<h2>ID검색</h2>
		<table border = "1">
			<tr>
				<th>아이디 검색</th>
				<td>
					<input type="text" id="idck">
					<button type="button" id="idckBtn">ID검색</button>
				</td>
			</tr>
		</table>
		
		<table border="1">
			<tr>
				<th>성별</th>
				<td><input type="text" id="gender" name="gender" readonly></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" id="age" name="age" readonly></td>
			</tr>
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
				확인 - <input type="text" id="pw2" name="pw2">
				</td>
			</tr>
		</table>
		<button type="button" id="btn">회원가입</button>
	</form>
	
</body>
</html>
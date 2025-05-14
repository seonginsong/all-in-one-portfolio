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
		<hr>
		<table border="1">
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="postcode" id="postcode" placeholder="우편번호">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" name="roadAddress"  id="roadAddress" placeholder="도로명주소">
					<input type="text" name="jibunAddress"  id="jibunAddress" placeholder="지번주소">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" name="detailAddress"  id="detailAddress" placeholder="상세주소">
					<input type="text" name="extraAddress"  id="extraAddress" placeholder="참고항목">
				</td>
			</tr>
		
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
	<!-- 카카오 주소 API 호출을 위한 CDN주소 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
	</script>
</body>
</html>
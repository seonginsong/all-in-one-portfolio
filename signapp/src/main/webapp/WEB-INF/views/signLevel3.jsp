<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/signature_pad@4.1.7/dist/signature_pad.umd.min.js"></script>
<script>
	$(document).ready(function () {
		// 캔버스에 사인 후 객체로 받는 SignaturePad 생성자
		// API 설명은 https://github.com/szimek/signature_pad 의 readme참고
		const signaturePad = new SignaturePad($('canvas')[0], {
			minWidth: 1,
		    maxWidth: 1,
		    penColor: 'rgb(0, 0, 0)'
		});
		// 캔버스 내용을 초기화하는 SignaturePad API clear()메서드
		$('#btnClear').click(function () {
			signaturePad.clear();
		});
		// AJax로 SignaturePad 객체 안의 사인 이미지를 서버로 전송
		$('#btnSign').click(function(){
			if(signaturePad.isEmpty()) {
				alert('사인을 먼저 해주세요');
			} else {
				// $.ajax({}).done(function(){}).fail(function(){})
				
				$.ajax({
					async : true // true면 비동기(백그라운드로 실행) -> 페이지가 멈추지 않음
					, url : '/addSign'
					, type : 'post'
					, data : {
						// 로그인 사용자 id, SignaturePad객체 안의 사인 이미지
						id : $('#id').text()
						, signImg : signaturePad.toDataURL() // 인수 생략시 png 따로 지정해주려면 ("image/jpeg")...
					}
				}).done(function(data){
					alert(data);
					// js로 페이지 이동
					location.href='/docView';
				}).fail(function(){
					signaturePad.clear();
					alert('결제 실패');
				});
			}
		});
	});
</script>
</head>
<body>
	<!-- id : signlevel3 로그인 사용자 id -->
	<div id="id">
		manager <!-- <input type="text" name="id"> -->
	</div>
	<canvas style="border: 1px solid #000000;" id="signCanvas"></canvas>
	<button type="button" id="btnClear">Clear</button>
	<button type="button" id="btnSign">Sign</button>
</body>
</html>
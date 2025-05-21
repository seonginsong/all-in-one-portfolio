<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addSample</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#addBtn').click(function() {
			if($('#name').val().length < 4) {
				alert('name은 4자 이상');
				return;
			}
			if(!$.isNumeric($('#age').val()) || $('#age').val() < 0 || $('#age').val() > 200) {
				alert('age는 0 ~ 200 사이 숫자만');
				return;
			}
			$('#addForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>addSample</h1>
	<span>${errMsg}</span>
	<form id="addForm" method="post" action="/addSample">
		<div>
			name : <input type="text" id="name" name="name">
			<span>${nameErrMsg}</span>
		</div>
		<div>
			age : <input type="number" id="age" name="age" min="0" max="200">
			<span>${ageErrMsg}</span>
		</div>
		<div>
			<button type="button" id="addBtn">입력</button>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form id="addBoard" action="/addBoard" method="post">
<div>
	title : <input type="text" id="boardTitle" name="boardTitle">
</div>
<div>
	content : <input type="text" id="boardContent" name="boardContent">
</div>
<div>
	user : <input type="text" id="boardUser" name="boardUser">
</div>
<button type="submit" id="addBtn">추가</button>
<button type="button" id="back">리스트로</button>
</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$('#back').click(function(){
		location.href = "/boardList";
	});
	$('#addBtn').click(function(){
		$('#addBoard').submit();
	});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>update</h1>
<form id="updateBoard" action="/updateBoard" method="post">
<div>
	No : <input type="text" id="boardNo" name="boardNo" value="${board.boardNo}" readonly>
</div>
<div>
	title : <input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle}">
</div>
<div>
	content : <input type="text" id="boardContent" name="boardContent" value="${board.boardContent}">
</div>
<div>
	user : <input type="text" id="boardUser" name="boardUser" value="${board.boardUser}">
</div>
<button type="submit" id="updateBtn">수정</button>
<button type="button" id="back">뒤로</button>
</form>
<a href="/boardList">리스트로</a>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$('#back').click(function(){
		location.href = "/boardOne?boardNo=" + ${board.boardNo}
	});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>insertPollForm</title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>투표 프로그램</h1>
	<hr>
	<h2>설문 작성</h2>
	<form method="post" action="/poll/insertPollAction.jsp">
		<table class="table table-striped table-bordered">
			<tr>
				<td>질문</td>
				<td colspan="2">
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<td rowspan="8">항목</td>
				<td>1) <input type="text" name="content"></td>
				<td>2) <input type="text" name="content"></td>
			</tr>
			<tr>
				<td>3) <input type="text" name="content"></td>
				<td>4) <input type="text" name="content"></td>
			</tr>
			<tr>
				<td>5) <input type="text" name="content"></td>
				<td>6) <input type="text" name="content"></td>
			</tr>
			<tr>
				<td>7) <input type="text" name="content"></td>
				<td>8) <input type="text" name="content"></td>
			</tr>
			<tr>
				<td>시작일</td>
				<td><input type="date" name="startdate"></td>
			</tr>
			<tr>
				<td>종료일</td>
				<td><input type="date" name="enddate"></td>
			</tr>
			<tr>
				<td>복수 투표</td>
				<td>
					<input type="radio" name="type" value="1">yes
					<input type="radio" name="type" value="0">no
				</td>
			</tr>
		</table>
		<button type="submit">작성하기</button>
		<button type="reset">다시쓰기</button>
		<a href="/poll/pollList.jsp">리스트</a>
	</form>
</body>
</html>
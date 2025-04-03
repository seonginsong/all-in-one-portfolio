<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page import="dto.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Integer num = Integer.parseInt(request.getParameter("num"));
	QuestionDao questiondao = new QuestionDao();
	ItemDao itemdao = new ItemDao();
	Question question = new Question();
	ArrayList<Item> list = new ArrayList<>();
	ArrayList<Item> list2 = new ArrayList<>();
	question = questiondao.selectQuestion(num);
	System.out.println(question.getTitle());
	
	list = itemdao.selectItem(num);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form method="post" action="/poll/updatePollAction.jsp">
	<h1>Update Poll</h1>
		<table border="1">
			<tr>
				<td>질문</td>
				<td colspan="2">
					<input type="text" name="title" value="<%=question.getTitle()%>">
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
				<td><input type="date" name="startdate" value="<%=question.getStartdate()%>"></td>
			</tr>
			<tr>
				<td>종료일</td>
				<td><input type="date" name="enddate" value="<%=question.getEnddate()%>"></td>
			</tr>
			<tr>
				<td>복수 투표</td>
				<td>
					<input type="radio" name="type" value="1" <% if (question.getType() == 1) {%> checked <%}%>>yes
					<input type="radio" name="type" value="0" <% if (question.getType() == 0) {%> checked <%}%>>no
				</td>
			</tr>
		</table>
		<button type="submit">수정하기</button>
		</form>
</body>
</html>
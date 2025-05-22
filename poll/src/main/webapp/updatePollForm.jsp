<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page import="dto.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("qnum"));
	
	QuestionDao questiondao = new QuestionDao();
	ItemDao itemdao = new ItemDao();
	
	Question question = questiondao.selectQuestion(num);
	ArrayList<Item> list = itemdao.selectItem(num);
	System.out.println(list.get(1).getContent());
	System.out.println(list.size());
	String[] content = new String[8];
	for(int i=0; i<8; i++){
		if(i < list.size()) {
			content[i] = list.get(i).getContent();
		} else {
			content[i] = "";
		}
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<form method="post" action="/poll/updatePollAction.jsp?qnum=<%=num%>">
	<h1>Update Poll</h1>
		<table class="table table-striped table-bordered">
			<tr>
				<td>질문</td>
				<td colspan="2">
					<input type="text" name="title" value="<%=question.getTitle()%>">
				</td>
			</tr>
			<tr>
				<td rowspan="8">항목</td>
				<td>1) <input type="text" name="content" value="<%=content[0]%>"></td>
				<td>2) <input type="text" name="content" value="<%=content[1]%>"></td>
			</tr>
			<tr>
				<td>3) <input type="text" name="content" value="<%=content[2]%>"></td>
				<td>4) <input type="text" name="content" value="<%=content[3]%>"></td>
			</tr>
			<tr>
				<td>5) <input type="text" name="content" value="<%=content[4]%>"></td>
				<td>6) <input type="text" name="content" value="<%=content[5]%>"></td>
			</tr>
			<tr>
				<td>7) <input type="text" name="content" value="<%=content[6]%>"></td>
				<td>8) <input type="text" name="content" value="<%=content[7]%>"></td>
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
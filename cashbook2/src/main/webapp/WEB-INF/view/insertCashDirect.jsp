<%@page import="java.util.ArrayList"%>
<%@page import="dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook2/css/style.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>수입/지출 이력 추가</h1>
	<%
		if(request.getParameter("msg") != null) {
	%>
			입력값이 없는 항목이 존재합니다.
	<%
		}
	%>
	<form method="get" action="<%=request.getContextPath()%>/insertCashDirect">
		kind
		<select name="kind">
			<option value="" <%= (request.getAttribute("kind") == null || request.getAttribute("kind").equals("")) ? "selected" : "" %>>:::선택:::</option>
			<option value="수입" <%= "수입".equals(request.getAttribute("kind")) ? "selected" : "" %>>수입</option>
			<option value="지출" <%= "지출".equals(request.getAttribute("kind")) ? "selected" : "" %>>지출</option>
		</select>
		<button type="submit">수입/지출 선택</button>
	</form>
	
	
	<form method="post" action="<%=request.getContextPath()%>/insertCash">
		cashDate : <input type="date" name="cashDate">
		category : 
		<select name="category_no">
		<%
				if(request.getAttribute("list") != null) {
					for(Category c : (ArrayList<Category>)request.getAttribute("list")) {
			%>
						<option value="<%=c.getCategoryNo()%>"><%=c.getTitle()%></option>
			<%	
					}
				}
			%>
		</select>
		금액 : <input type="number" name="amount">원<hr>
		메모 : <input type="text" name="memo">
		<input type="color" name="color">
		<button type="submit">수입/지출 입력</button>
	
	</form>
</body>
</html>
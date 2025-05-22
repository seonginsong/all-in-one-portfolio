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
	<h1>수입/지출 이력 변경</h1>
	<%
		if(request.getParameter("msg") != null) {
	%>
			입력값이 없는 항목이 존재합니다.
	<%
		}
	%>
	<form method="get" action="<%=request.getContextPath()%>/updateCash">
	    <input type="hidden" name="cashNo" value="<%=request.getAttribute("cashNo")%>">
		<input type="hidden" name="y" value="<%=request.getAttribute("y")%>">
		<input type="hidden" name="m" value="<%=request.getAttribute("m")%>">
		<input type="hidden" name="d" value="<%=request.getAttribute("d")%>">
		kind
		<select name="kind">
			<%
				if(request.getParameter("kind") != null) {
					if("수입".equals(request.getParameter("kind"))) {
			%>
					<option value="수입" selected>수입</option>
			<%
					} else {
			%>
					<option value="수입">수입</option>
			<%
					}
				} else {
			%>
					<option value="수입" <%= "수입".equals(request.getAttribute("kind")) ? "selected" : "" %>>수입</option>
			<%
				}
			%>
			<%
				if(request.getParameter("kind") != null) {
					if("지출".equals(request.getParameter("kind"))) {
			%>
					<option value="지출" selected>지출</option>
			<%
					} else {
			%>
					<option value="지출">지출</option>
			<%
					}
				} else {
			%>
					<option value="지출" <%= "지출".equals(request.getAttribute("kind")) ? "selected" : "" %>>지출</option>
			<%
				}
			%>
		</select>
		<button type="submit">수입/지출 선택</button>
	</form>
	
	
	<form method="post" action="<%=request.getContextPath()%>/updateCash?cashNo=<%=request.getAttribute("cashNo")%>">
		cashDate : <input type="date" name="cashDate" value="<%=request.getAttribute("cashDate")%>">
		category : 
		<select name="category_no">
		<%
				if(request.getAttribute("list") != null) {
					for(Category cg : (ArrayList<Category>)request.getAttribute("list")) {
			%>
						<option value="<%=cg.getCategoryNo()%>"
			<%
							if(cg.getCategoryNo() == (Integer)request.getAttribute("categoryNo")) {
			%>
								selected
			<%
							} else {							
							}
			%>
						><%=cg.getTitle()%></option>
			<%	
					}
				}
			%>
		</select>
		금액 : <input type="number" name="amount" value="<%=request.getAttribute("amount")%>">원<hr>
		메모 : <input type="text" name="memo" value="<%=request.getAttribute("memo")%>">
		<input type="color" name="color" value="<%=request.getAttribute("color")%>">
		<button type="submit">수입/지출 변경</button>
	
	</form>
</body>
</html>
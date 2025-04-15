<%@page import="java.util.ArrayList"%>
<%@page import="dto.Category"%>
<%@page import="model.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}


	// insertCashForm.jsp -> kind 선택(String kind)
	String kind = request.getParameter("kind");
	// insertCashForm.jsp에서 kind 선택 후 재요청 
	// DB : 선택된 kind의 title 목록
	
	// 카테고리 목록 뽑기
	ArrayList<Category> list = null;
	if(kind != null) { // insertCashForm.jsp에서 kind 선택 후 재요청
		// DB : 선택된 kind의 title 목록
		CategoryDao categoryDao = new CategoryDao();
		list = categoryDao.selectCategoryListByKind(kind);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook/css/style.css">
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
	<form method="post" action="/cashbook/cash/insertCashFormDirect.jsp">
		kind
		<select name="kind">
			<option value="" <%= (kind == null || kind.equals("")) ? "selected" : "" %>>:::선택:::</option>
			<option value="수입" <%= "수입".equals(kind) ? "selected" : "" %>>수입</option>
			<option value="지출" <%= "지출".equals(kind) ? "selected" : "" %>>지출</option>
		</select>
		<button type="submit">수입/지출 선택</button>
	</form>
	
	
	<form method="post" action="/cashbook/cash/insertCashAction.jsp">
		cashDate : <input type="date" name="cashDate">
		category : 
		<select name="category_no">
		<%
				if(list != null) {
					for(Category c : list) {
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
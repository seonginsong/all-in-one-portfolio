<%@page import="model.CashDao"%>
<%@page import="dto.Cash"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Category"%>
<%@page import="model.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int cashNo = Integer.parseInt(request.getParameter("cashNo"));
	
	// insertCashForm.jsp -> kind 선택(String kind)
	String kind = request.getParameter("kind");
	// insertCashForm.jsp에서 kind 선택 후 재요청 
	// DB : 선택된 kind의 title 목록
	
	
	Cash cs = new Cash();
	CashDao cashDao = new CashDao();
	cs = cashDao.selectCashOne(cashNo);
	
	
	// 카테고리 목록 뽑기
	ArrayList<Category> list = null;
	if(kind != null) { // insertCashForm.jsp에서 kind 선택 후 재요청
		// DB : 선택된 kind의 title 목록
		CategoryDao categoryDao = new CategoryDao();
		list = categoryDao.selectCategoryListByKind(kind);
	} else {
		CategoryDao categoryDao = new CategoryDao();
		list = categoryDao.selectCategoryListByKind(cs.getKind());
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
	<h1>수입/지출 이력 변경</h1>
	<%
		if(request.getParameter("msg") != null) {
	%>
			입력값이 없는 항목이 존재합니다.
	<%
		}
	%>
	<form method="post" action="/cashbook/cash/updateCashForm.jsp?cashNo=<%=cashNo%>">
		kind
		<select name="kind">
			<option value="수입" <%= "수입".equals(cs.getKind()) ? "selected" : "" %>>수입</option>
			<option value="지출" <%= "지출".equals(cs.getKind()) ? "selected" : "" %>>지출</option>
		</select>
		<button type="submit">수입/지출 선택</button>
	</form>
	
	
	<form method="post" action="/cashbook/cash/updateCashAction.jsp?cashNo=<%=cashNo%>">
		cashDate : <input type="date" name="cashDate" value="<%=cs.getCashDate()%>">
		category : 
		<select name="category_no">
		<%
				if(list != null) {
					for(Category cg : list) {
			%>
						<option value="<%=cg.getCategoryNo()%>"><%=cg.getTitle()%></option>
			<%	
					}
				}
			%>
		</select>
		금액 : <input type="number" name="amount" value="<%=cs.getAmount()%>">원<hr>
		메모 : <input type="text" name="memo" value="<%=cs.getMemo()%>">
		<input type="color" name="color" value="<%=cs.getColor()%>">
		<button type="submit">수입/지출 변경</button>
	
	</form>
</body>
</html>
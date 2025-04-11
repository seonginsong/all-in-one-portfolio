<%@page import="dto.Cash"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CashDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int y = Integer.parseInt(request.getParameter("y"));
	int m = Integer.parseInt(request.getParameter("m"));
	int d = Integer.parseInt(request.getParameter("d"));
	
	String kind = "";
	String strKind = request.getParameter("kind");
	if(strKind != null && !request.getParameter("kind").equals("전체")) {
		kind = request.getParameter("kind");
	} else {
		kind = "";
	}
	
	CashDao cashDao = new CashDao();
	ArrayList<Cash> list = cashDao.selectCashByDate(y, m, d, kind);
	int sum1 = cashDao.selectSumAmountByDate(y, m, d, "수입");
	int sum2 = cashDao.selectSumAmountByDate(y, m, d, "지출");
	int sum3 = cashDao.selectSumAmountByDate(y, m, d, "");
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
	<h1><%=y%>년<%=m%>월<%=d%>일 Date List</h1>
	수입 : <%=sum1%>원
	지출 : <%=sum2%>원
	총합 : <%=sum3%>원
	<table border="1">
			<tr>
				<th>cash no</th>
				<th>kind</th>
				<th>title</th>
				<th>amount</th>
				<th>memo</th>
				<th>color</th>
				<th>변경하기</th>
				<th>삭제하기</th>
			</tr>
			<%
				for(Cash c : list) {
			%>
					<tr>
						<td><%=c.getCashNo()%></td>
						<td><%=c.getKind()%></td>
						<td><%=c.getTitle()%></td>
						<td><%=c.getAmount()%>원</td>
						<td><%=c.getMemo()%></td>
						<td><input type="color" value="<%=c.getColor()%>" disabled></button></td>
						<td><button type="button" onclick="location.href='/cashbook/updateCashForm.jsp?cashNo=<%=c.getCashNo()%>'">변경하기</button></td>
						<td><button type="button" class="delete-btn" onclick="location.href='/cashbook/deleteCash.jsp?cashNo=<%=c.getCashNo()%>'">삭제하기</button>
						</td>
					</tr>
			<%	
				}
			%>
		</table>
		<form action="/cashbook/dateList.jsp" class="search-bar">
			<input type="hidden" name="y" value="<%=y%>">
			<input type="hidden" name="m" value="<%=m%>">
			<input type="hidden" name="d" value="<%=d%>">
			<select name="kind">
				<option value="전체" <%= kind.equals("") ? "selected" : "" %>>전체</option>
				<option value="수입" <%= kind.equals("수입") ? "selected" : "" %>>수입</option>
				<option value="지출" <%= kind.equals("지출") ? "selected" : "" %>>지출</option>
			</select>
			<button type="submit">검색</button><a href="/cashbook/cash/insertCashForm.jsp?y=<%=y%>&m=<%=m%>&d=<%=d%>">추가</a>
		</form>
</body>
</html>
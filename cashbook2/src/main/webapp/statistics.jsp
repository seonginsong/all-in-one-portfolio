<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%
	StaticDao staticDao = new StaticDao();
	int allPlusSum = staticDao.selectStaticAll("수입");
	int allMinusSum = staticDao.selectStaticAll("지출");
	System.out.println("allPlusSum :"+allPlusSum);
	System.out.println("allMinusSum :"+allMinusSum);
	int year = 0;
	if(request.getParameter("year") == null) {
		year = 2025;
	} else {
		year = Integer.parseInt(request.getParameter("year"));
	}
	ArrayList<Static> yearList = staticDao.selectStaticByYear();
	ArrayList<Static> monthList = staticDao.selectStaticByMonth();
	ArrayList<Static> sList = staticDao.selectStaticBySYear(year);
	
	ArrayList<Integer> yearOptionList = staticDao.selectDistinctYears();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook/css/statisticsStyle.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>통계</h1>
	<div style="text-align: center;" class="section">전체 수입/지출 총액</div>
	<div class="table-wrap">
	<table>
		<tr>
			<th>총 수입</th>
			<td><%=allPlusSum%>원</td>
		</tr>
		<tr>
			<th>총 지출</th>
			<td><%=allMinusSum%>원</td>
		</tr>
		<tr>
			<th>총 이익</th>
			<td><%=allPlusSum - allMinusSum%>원</td>
		</tr>
	</table>
	</div>
	<div style="text-align: center;" class="section">년도별 수입/지출 총액</div>
	<div class="table-wrap">
	<table border="1">
		<tr>
			<th>년도</th>
			<th>분류</th>
			<th>금액</th>
		</tr>
		<%
			for(Static s : yearList) {
		%>
			<tr>
				<td><%=s.getYear()%></td>
				<td><%=s.getKind()%></td>
				<td><%=s.getAmount()%>원</td>
			</tr>
		<%
			}
		%>
	</table>
	</div>
	<div style="text-align: center;" class="section">월별 수입/지출 총액</div>
	<div class="table-wrap">
	<table border="1">
		<tr>
			<th>월</th>
			<th>분류</th>
			<th>금액</th>
		</tr>
		<%
			for(Static s : monthList) {
		%>
			<tr>
				<td><%=s.getMonth()%></td>
				<td><%=s.getKind()%></td>
				<td><%=s.getAmount()%>원</td>
			</tr>
		<%
			}
		%>
	</table>
	</div>
	<div style="text-align: center;" class="section">
	<form action="/cashbook/statistics.jsp?year=<%=request.getParameter("year")%>">
	<select name="year">
	<%
		for(Integer y : yearOptionList) {
	%>
		<option value="<%=y%>" <%= (y == year ? "selected" : "") %>><%=y%></option>
	<%
		}
	%>
	</select>년의 월별 수입/지출 총액
	<button type="submit">년도 선택</button>
	</div>
	<div class="table-wrap">
	<table border="1">
		<tr>
			<th>월</th>
			<th>분류</th>
			<th>금액</th>
		</tr>
		<%
			for(Static s : sList) {
		%>
			<tr>
				<td><%=s.getMonth()%></td>
				<td><%=s.getKind()%></td>
				<td><%=s.getAmount()%>원</td>
			</tr>
		<%
			}
		%>
	</table>
	</div>
</body>
</html>
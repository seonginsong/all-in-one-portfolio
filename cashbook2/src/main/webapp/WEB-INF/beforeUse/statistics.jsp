<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%
	StaticDao staticDao = new StaticDao();
	int allPlusSum = staticDao.selectStaticAll("수입");
	int allMinusSum = staticDao.selectStaticAll("지출");
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
	DecimalFormat df = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook2/css/statisticsStyle.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>통계</h1>
	<!-- 테이블 전체 감싸는 div -->
	<div class="statistics-container">
	<!-- 왼쪽: 상단 3개 테이블 -->
	<div class="left-tables">
	<div style="text-align: center;" class="section">전체 수입/지출 총액</div>
	<div class="table-wrap">
	<table>
		<tr>
			<th>총 수입</th>
			<td><%=df.format(allPlusSum)%>원</td>
		</tr>
		<tr>
			<th>총 지출</th>
			<td><%=df.format(allMinusSum)%>원</td>
		</tr>
		<tr>
			<th>총 이익</th>
			<td><%=df.format(allPlusSum - allMinusSum)%>원</td>
		</tr>
	</table>
	</div>
	
	
	
	<div style="text-align: center;" class="section">월별 수입/지출 총액</div>
	<div class="table-wrap">
	<table border="1">
		<tr>
			<th>월</th>
			<th>수입</th>
			<th>지출</th>
			<th>금액</th>
		</tr>
		<%
		int currentMonth = -1;
		int incomeMonth = 0;
		int expenseMonth = 0;

		for (int i = 0; i < monthList.size(); i++) {
			Static s = monthList.get(i);

			// 월 바뀌면 이전 월 출력
			if (currentMonth != -1 && currentMonth != s.getMonth()) {
	%>
	<tr>
		<td><%=currentMonth%></td>
		<td><%=df.format(incomeMonth)%>원</td>
		<td><%=df.format(expenseMonth)%>원</td>
		<td><%=df.format(incomeMonth-expenseMonth)%>원</td>
	</tr>
	<%
				// 값 초기화
				incomeMonth = 0;
				expenseMonth = 0;
			}

			// 현재 월 갱신
			currentMonth = s.getMonth();

			// 수입/지출 분류
			if ("수입".equals(s.getKind())) {
				incomeMonth = s.getAmount();
			} else if ("지출".equals(s.getKind())) {
				expenseMonth = s.getAmount();
			}
		}

		// 마지막 월 출력
		if (currentMonth != -1) {
	%>
	<tr>
		<td><%=currentMonth%></td>
		<td><%=df.format(incomeMonth)%>원</td>
		<td><%=df.format(expenseMonth)%>원</td>
		<td><%=df.format(incomeMonth-expenseMonth)%>원</td>
	</tr>
	<%
		}
	%>
	</table>
	</div>
	</div>
	
	
	<!-- 오른쪽: 선택 년도별 월별 테이블 -->
	<div class="right-table">
	<div style="text-align: center;" class="section">년도별 수입/지출 총액</div>
	<div class="table-wrap">
	<table border="1">
		<tr>
		<th>년도</th>
		<th>수입</th>
		<th>지출</th>
		<th>총액</th>
	</tr>
	<%
		int currentYear = -1;
		int incomeYear = 0;
		int expenseYear = 0;

		for (int i = 0; i < yearList.size(); i++) {
			Static s = yearList.get(i);

			// 연도가 바뀌면 이전 연도 출력
			if (currentYear != -1 && currentYear != s.getYear()) {
	%>
	<tr>
		<td><%=currentYear%></td>
		<td><%=df.format(incomeYear)%>원</td>
		<td><%=df.format(expenseYear)%>원</td>
		<td><%=df.format(incomeYear-expenseYear)%>원</td>
	</tr>
	<%
				// 값 초기화
				incomeYear = 0;
				expenseYear = 0;
			}

			// 현재 연도 갱신
			currentYear = s.getYear();

			// 수입/지출 분류
			if ("수입".equals(s.getKind())) {
				incomeYear = s.getAmount();
			} else if ("지출".equals(s.getKind())) {
				expenseYear = s.getAmount();
			}
		}

		// 마지막 연도 출력
		if (currentYear != -1) {
	%>
	<tr>
		<td><%=currentYear%></td>
		<td><%=df.format(incomeYear)%>원</td>
		<td><%=df.format(expenseYear)%>원</td>
		<td><%=df.format(incomeYear-expenseYear)%>원</td>
	</tr>
	<%
		}
	%>
	</table>
	</div>
	
	
	<div style="text-align: center;" class="section">
	<form action="/cashbook2/statistics.jsp?year=<%=request.getParameter("year")%>">
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
			<th>수입</th>
			<th>지출</th>
			<th>금액</th>
		</tr>
	<%
		int currentS = -1;
		int incomeS = 0;
		int expenseS = 0;

		for (int i = 0; i < sList.size(); i++) {
			Static s = sList.get(i);

			// 월 바뀌면 이전 월 출력
			if (currentS != -1 && currentS != s.getMonth()) {
	%>
	<tr>
		<td><%=currentS%></td>
		<td><%=df.format(incomeS)%>원</td>
		<td><%=df.format(expenseS)%>원</td>
		<td><%=df.format(incomeS-expenseS)%>원</td>
	</tr>
	<%
				// 값 초기화
				incomeS = 0;
				expenseS = 0;
			}

			// 현재 월 갱신
			currentS = s.getMonth();

			// 수입/지출 분류
			if ("수입".equals(s.getKind())) {
				incomeS = s.getAmount();
			} else if ("지출".equals(s.getKind())) {
				expenseS = s.getAmount();
			}
		}

		// 마지막 월 출력
		if (currentS != -1) {
	%>
	<tr>
		<td><%=currentS%></td>
		<td><%=df.format(incomeS)%>원</td>
		<td><%=df.format(expenseS)%>원</td>
		<td><%=df.format(incomeS-expenseS)%>원</td>
	</tr>
	<%
		}
	%>
	</table>
	</div>
	</div>
</div>
</body>
</html>
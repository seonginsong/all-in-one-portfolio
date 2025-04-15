<%@page import="dto.Cash"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CashDao"%>
<%@page import="dto.Month"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
	// 현재 월의 기본값 설정(1일)
	Calendar firstDate = Calendar.getInstance();
	firstDate.set(Calendar.DATE, 1);
	
	String strYear = request.getParameter("targetYear");
	String strMonth = request.getParameter("targetMonth");
	
	int year = (strYear != null) ? Integer.parseInt(strYear) : firstDate.get(Calendar.YEAR);
	int month = (strMonth != null) ? Integer.parseInt(strMonth) : firstDate.get(Calendar.MONTH);

	// Calendar 객체에 반영 (월은 0부터 시작)
	firstDate.set(Calendar.YEAR, year);
	firstDate.set(Calendar.MONTH, month);
	
	//targetMonth가 있을 경우 : 월은 targetMonth
	System.out.println("targetMonth: "+request.getParameter("targetMonth"));
	if(request.getParameter("targetMonth") != null) {
		firstDate.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth")));
	}
	
	// 이전/다음 달 계산
	Calendar prev = (Calendar) firstDate.clone();
	prev.add(Calendar.MONTH, -1);
	int prevYear = prev.get(Calendar.YEAR);
	int prevMonth = prev.get(Calendar.MONTH);

	Calendar next = (Calendar) firstDate.clone();
	next.add(Calendar.MONTH, 1);
	int nextYear = next.get(Calendar.YEAR);
	int nextMonth = next.get(Calendar.MONTH);
	
	// 달력 형식에 필요한 값들
	int lastDate = firstDate.getActualMaximum(Calendar.DATE);
	System.out.println("마지막 날짜:"+lastDate);
	
	// 요일(1 - 일 2- 월)
	int dayOfWeek = firstDate.get(Calendar.DAY_OF_WEEK);
	
	// DTO
	Cash cash = new Cash();
	Month m = new Month();
	m.setLastDate(lastDate);
	m.setDayOfWeek(dayOfWeek);
	int startBlank = m.getStartBlank();
	int totalCell = m.getTotalCell();
	int d = 0;
	
	//DAO
	CashDao cashDao = new CashDao();
	ArrayList<Cash> list = cashDao.selectCashByMonth(firstDate.get(Calendar.YEAR), firstDate.get(Calendar.MONTH) + 1);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook/css/monthListStyle.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>월별 리스트</h1>
	<div class="calendar-nav" style="text-align: center">
		<a href="/cashbook/monthList.jsp?targetYear=<%=prevYear%>&targetMonth=<%=prevMonth%>">이전달</a>
		<%=firstDate.get(Calendar.YEAR)%>년<%=firstDate.get(Calendar.MONTH)+1%>월
		<a href="/cashbook/monthList.jsp?targetYear=<%=nextYear%>&targetMonth=<%=nextMonth%>">다음달</a>
	</div>
	<div class="calendar-container">
	<table class="calendar-table" border="1">
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>		
		</tr>
		<tr>
			<%
				for(int i=1; i<=totalCell; i=i+1) {
			%>
			<td>
				<%	
					d = i - startBlank;
					if(d > 0 && d <= lastDate) {
				%>
						<div>
							<a href="/cashbook/dateList.jsp?y=<%=year%>&m=<%=month+1%>&d=<%=d%>"><%=d%></a>
						</div>
						
						<div class="day-box">
						<%
								for (Cash c : list) {
									// 날짜에서 일(day)만 추출해서 비교
									int day = Integer.parseInt(c.getCashDate().substring(8, 10)); // "YYYY-MM-DD"
									if(day == d) {
						%>
										<div class="cash-item" style="color:<%=c.getColor()%>"><%=c.getKind()%>-<%=c.getTitle()%> : <%=c.getAmount()%>원</div>
						<%
									}
								}
						%>
						</div>
						<%
							}
						%>
			</td>	
			<%		
					if(i%7==0) {
			%>
						</tr><tr>		
			<%			
					}
				}
			%>
		</tr>
	</table>
	</div>
</body>
</html>
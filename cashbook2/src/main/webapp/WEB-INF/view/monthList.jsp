<%@page import="java.util.ArrayList"%>
<%@page import="dto.Cash"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/cashbook2/css/monthListStyle.css">
<title></title>
</head>
<body>
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>월별 리스트</h1>
	<div class="calendar-nav" style="text-align: center">
		<a href='<%=request.getContextPath()%>/monthList?targetYear=<%=(Integer)request.getAttribute("prevYear")%>&targetMonth=<%=(Integer)request.getAttribute("prevMonth")%>'>이전달</a>
		<%=request.getAttribute("nowYear")%>년<%=(Integer)request.getAttribute("nowMonth")+1%>월
		<a href='<%=request.getContextPath()%>/monthList?targetYear=<%=(Integer)request.getAttribute("nextYear")%>&targetMonth=<%=(Integer)request.getAttribute("nextMonth")%>'>다음달</a>
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
				for(int i=1; i<=(Integer)request.getAttribute("totalCell"); i=i+1) {
			%>
			<td>
				<%	
					int d = 0;
					d = i - (Integer)request.getAttribute("startBlank");
					if(d > 0 && d <= (Integer)request.getAttribute("lastDate")) {
				%>
						<div>
							<a href='<%=request.getContextPath()%>/dateList?y=<%=(Integer)request.getAttribute("year")%>&m=<%=(Integer)request.getAttribute("month")+1%>&d=<%=d%>'><%=d%></a>
						</div>
						
						<div class="day-box">
						<%
								for (Cash c : (ArrayList<Cash>)request.getAttribute("list")) {
									// 날짜에서 일(day)만 추출해서 비교
									int day = Integer.parseInt(c.getCashDate().substring(8, 10)); // "YYYY-MM-DD"
									if(day == d) {
						%>
										<div class="cash-item"><a href="<%=request.getContextPath()%>/cashOne?cashNo=<%=c.getCashNo()%>" style="color:<%=c.getColor()%>"><%=c.getKind()%>-<%=c.getTitle()%> : <%=c.getAmount()%>원</a></div>
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
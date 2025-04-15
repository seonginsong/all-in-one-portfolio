<%@page import="model.ReceitDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dto.Cash"%>
<%@page import="model.CashDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String adminId = (String)session.getAttribute("loginId");
	
	if(adminId == null) { //로그아웃 상태라면
		response.sendRedirect("/cashbook/loginForm.jsp");
		//로그인 페이지로 리다이렉트
		return;
	}
	
	int cashNo = Integer.parseInt(request.getParameter("cashNo"));
	// cash 목록 뽑기
	Cash c = new Cash();
	CashDao cashDao = new CashDao();
	c = cashDao.selectCashOne(cashNo);
	
	// receit 있는지 확인
	ReceitDao receitDao = new ReceitDao();
	int cnt = receitDao.selectReceitCount(cashNo);
	String filename = receitDao.selectReceitFilename(cashNo);
	
	// 금액 형식
	DecimalFormat df = new DecimalFormat("###,###");
	
	String arr[] = c.getCashDate().split("-");
	
	System.out.println(arr[0]+arr[1]+arr[2]);
	int y = Integer.parseInt(arr[0]);
	int m = Integer.parseInt(arr[1]);
	int d = Integer.parseInt(arr[2]);
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
	<h1>Cash 상세보기</h1>
	<%
		if(request.getParameter("nmsg") != null) {
	%>		
		<div>사진을 등록하지 않았습니다.</div>
	<%
		} else if(request.getParameter("cmsg") != null) {
	%>
		<div>영수증이 이미 존재합니다.</div>
	<%
		}
	%>	
	<table border="1">
		<tr>
			<td>CashNo</td>
			<td><%=c.getCashNo()%></td>
		</tr>
		<tr>
			<td>CategoryKind</td>
			<td><%=c.getKind()%></td>
		</tr>
		<tr>
			<td>CategoryTitle</td>
			<td><%=c.getTitle()%></td>
		</tr>
		<tr>
			<td>CashDate</td>
			<td><%=c.getCashDate()%></td>
		</tr>
		<tr>
			<td>amount</td>
			<td><%=df.format(c.getAmount())%>원</td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=c.getMemo()%></td>
		</tr>
		<tr>
			<td>color</td>
			<td><input type="color" value="<%=c.getColor()%>" disabled></td>
		</tr>
		<tr>
			<td>receiptcheck</td>
			<td>
			<%
				if(cnt>0) {
			%>
					&#129534;
			<%
				} else {
			%>
					&#128683;
			<%
				}
			%>
			</td>
		</tr>
		<tr>
			<td>receipt</td>
			<td>
			<%
				if(filename != null) {
			%>
					<img src="/cashbook/upload/<%=filename%>">
			<%
				}
			%></td>
		</tr>
	</table>
	<div style="text-align: center">
	<button type="button" onclick="location.href='/cashbook/cash/updateCashForm.jsp?cashNo=<%=c.getCashNo()%>&y=<%=y%>&m=<%=m%>&d=<%=d%>'">수정하기</button>
	<button type="button" onclick="location.href='/cashbook/receit/insertReceitForm.jsp?cashNo=<%=c.getCashNo()%>&filename=<%=filename%>'">영수증추가</button>
	<button type="button" class="delete-btn" onclick="location.href='/cashbook/receit/deleteReceit.jsp?cashNo=<%=c.getCashNo()%>'">영수증삭제</button>
	</div>
</body>
</html>
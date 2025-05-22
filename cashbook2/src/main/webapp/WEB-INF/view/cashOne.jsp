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
			<td><%=request.getAttribute("cashNo")%></td>
		</tr>
		<tr>
			<td>CategoryKind</td>
			<td><%=request.getAttribute("kind")%></td>
		</tr>
		<tr>
			<td>CategoryTitle</td>
			<td><%=request.getAttribute("title")%></td>
		</tr>
		<tr>
			<td>CashDate</td>
			<td><%=request.getAttribute("cashDate")%></td>
		</tr>
		<tr>
			<td>amount</td>
			<td><%=request.getAttribute("amount")%>원</td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=request.getAttribute("memo")%></td>
		</tr>
		<tr>
			<td>color</td>
			<td><input type="color" value="<%=request.getAttribute("color")%>" disabled></td>
		</tr>
		<tr>
			<td>receiptcheck</td>
			<td>
			<%
				if((Integer)request.getAttribute("cnt")>0) {
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
				if(request.getAttribute("filename") != null) {
			%>
					<img src="<%=request.getContextPath()%>/upload/<%=request.getAttribute("filename")%>">
			<%
				}
			%></td>
		</tr>
	</table>
	<div style="text-align: center">
	<button type="button" onclick="location.href='<%=request.getContextPath()%>/updateCash?cashNo=<%=request.getAttribute("cashNo")%>&y=<%=request.getAttribute("y")%>&m=<%=request.getAttribute("m")%>&d=<%=request.getAttribute("d")%>'">수정하기</button>
	<button type="button" onclick="location.href='<%=request.getContextPath()%>/insertReceit?cashNo=<%=request.getAttribute("cashNo")%>'">영수증추가</button>
	<%
		if((Integer)request.getAttribute("cnt") > 0) {
	%>
	<button type="button" class="delete-btn" onclick="location.href='<%=request.getContextPath()%>/deleteReceit?cashNo=<%=request.getAttribute("cashNo")%>&filename=<%=request.getAttribute("filename")%>'">영수증삭제</button>
	<%
		}
	%>
	</div>
</body>
</html>
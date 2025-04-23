<%@page import="java.util.ArrayList"%>
<%@page import="dto.Cash"%>
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
	<h1><%=request.getAttribute("y")%>년<%=request.getAttribute("m")%>월<%=request.getAttribute("d")%>일 Date List</h1>
	수입 : <%=request.getAttribute("sumPlus")%>원
	지출 : <%=request.getAttribute("sumMinus")%>원
	총합 : <%=request.getAttribute("sumAll")%>원
	<table border="1">
			<tr>
				<th>cash no</th>
				<th>kind</th>
				<th>title</th>
				<th>amount</th>
				<th>memo</th>
				<th>color</th>
				<th>상세보기</th>
				<th>변경하기</th>
				<th>삭제하기</th>
			</tr>
			<%
				for(Cash c : (ArrayList<Cash>)request.getAttribute("list")) {
			%>
					<tr>
						<td><%=c.getCashNo()%></td>
						<td><%=c.getKind()%></td>
						<td><%=c.getTitle()%></td>
						<td><%=c.getAmount()%>원</td>
						<td><%=c.getMemo()%></td>
						<td><input type="color" value="<%=c.getColor()%>" disabled></button></td>
						<td><a href="<%=request.getContextPath()%>/cashOne?cashNo=<%=c.getCashNo()%>">자세히</a></td>
						<td><button type="button" onclick="location.href='<%=request.getContextPath()%>/updateCashForm?cashNo=<%=c.getCashNo()%>&y=<%=request.getAttribute("y")%>&m=<%=request.getAttribute("m")%>&d=<%=request.getAttribute("d")%>'">변경하기</button></td>
						<td><button type="button" class="delete-btn" onclick="location.href='<%=request.getContextPath()%>/deleteCash?cashNo=<%=c.getCashNo()%>&y=<%=request.getAttribute("y")%>&m=<%=request.getAttribute("m")%>&d=<%=request.getAttribute("d")%>'">삭제하기</button>
						</td>
					</tr>
			<%	
				}
			%>
		</table>
		<form action="<%=request.getContextPath()%>/dateList" class="search-bar">
			<input type="hidden" name="y" value="<%=request.getAttribute("y")%>">
			<input type="hidden" name="m" value="<%=request.getAttribute("m")%>">
			<input type="hidden" name="d" value="<%=request.getAttribute("d")%>">
			<select name="kind">
				<option value="전체" <%= request.getAttribute("kind").equals("") ? "selected" : "" %>>전체</option>
				<option value="수입" <%= request.getAttribute("kind").equals("수입") ? "selected" : "" %>>수입</option>
				<option value="지출" <%= request.getAttribute("kind").equals("지출") ? "selected" : "" %>>지출</option>
			</select>
			<button type="submit">검색</button><a href="<%=request.getContextPath()%>/insertCash?cashDate=<%=request.getAttribute("cashdate")%>">추가</a>
		</form>
</body>
</html>
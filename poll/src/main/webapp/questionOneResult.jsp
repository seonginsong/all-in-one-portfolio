<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%@ page import = "dto.*" %>
<%@ page import = "java.util.*" %>
<%
	// Controller : request 분석 + model 호출
	int qnum = Integer.parseInt(request.getParameter("qnum"));
	//1) questionOne
	QuestionDao questionDao = new QuestionDao();
	Question question = questionDao.selectQuestion(qnum);
	// 2) 1)의 itemList
	ItemDao itemDao = new ItemDao();
	ArrayList<Item> itemList = itemDao.selectItem(qnum);
	// 3) 총 투표수
	int totalCnt = itemDao.getTotalCountByQnum(qnum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title></title>
</head>
<body>
	<!-- nav.jsp 인클루드 -->
	<div>
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1><%=qnum%>번 설문 투표 결과</h1>
	<table class="table table-striped table-bordered">
		<tr>
			<td colspan="4">
				Q : <%=question.getTitle()%>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				총 투표수 : <%=totalCnt%>
			</td>
		</tr>
		<tr>
			<td>번호</td><td>내용</td><td>카운트(차트)</td><td>카운트</td>
		</tr>
		<%
			for(Item i : itemList) {
		%>
				<tr>
					<td><%=i.getInum()%></td>
					<td><%=i.getContent()%></td>
					<td>
						<!--각 count값에 대한 백분율  -->
						<%
							int percentage = (int)(Math.round((double)i.getCount() / (double)totalCnt * 100));
						
							for(int n=1; n<=percentage; n=n+1) {
						%>
								*
						<%
							}
						%>
					</td>
					<td><%=i.getCount()%></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="dto.*"%>
<%@page import="java.util.*"%>
<%
	// ?번 문제와 아이템들 출력
	// type=1 아이템의 타입을 checkbox
	// type=0 아이템의 타입을 radio
	
	// Controller Layer(request 분석 + Model Layer 호출/반환)
	int qnum = Integer.parseInt(request.getParameter("qnum"));
	// 1) questionOne
	QuestionDao questionDao = new QuestionDao();
	Question question = questionDao.selectQuestion(qnum);
	// 2) 1)의 itemList
	ItemDao itemDao = new ItemDao();
	ArrayList<Item> itemList = itemDao.selectItem(qnum);
%>

<!-- View Layer -->
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
	<div>	
	<jsp:include page="/inc/nav.jsp"></jsp:include>
	</div>
	<h1>투표하기</h1>
	<form action="/poll/updateItemAction.jsp" method="post">
	<input type="hidden" name="qnum" value="<%=qnum%>">
	<table class="table table-striped table-bordered">
		<tr>
			<td>
				Q : <%=question.getTitle()%>
				(<%=question.getType() == 1 ? "복수투표가능" : "복수투표불가"%>)
			</td>
		</tr>
		<tr>
			<td>
				<%
					for(Item i : itemList) {
				%>
						<div>
							<%
								if(question.getType() == 0) { // type=radio
							%>
									<input type="radio" value="<%=i.getInum()%>" name="inum">
							<%	
								} else { // type = checkbox
							%>
									<input type="checkbox" value="<%=i.getInum()%>" name="inum">
							<%		
								}
							%>
							<%=i.getContent()%>
						</div>
				<%
					}
				%>
			</td>
		</tr>
	</table>
	<button type="submit">투표하기</button>
	</form>
</body>
</html>
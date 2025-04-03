<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%
	// question 테이블 리스트 -> 페이징 -> title 링크(stardate <= 오늘 날짜 <= enddate) -> 투표 프로그램
	
	// 현재 페이지 설정
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	// 페이징 정보 설정
	int rowPerPage = 3; // 한 페이지 당 보여줄 행 개수
	Paging paging = new Paging();
	paging.setCurrentPage(currentPage);
	paging.setRowPerPage(rowPerPage);
	
	// Dao 객체 생성
	QuestionDao questionDao = new QuestionDao();
	
	// 마지막 페이지 설정
	int lastPage = paging.getLastPage((questionDao.getTotalQuestion()));
	
	// 오늘 날짜 가져오기
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String todayStr = sdf.format(date);
	Date today = sdf.parse(todayStr);
	
	// 해당 페이지에 속한 리스트 조회
	ArrayList<Question> list = questionDao.selectQuestionList(paging);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pollList</title>
</head>
<body>
	<h1>설문 리스트</h1>
	<table border="1">
		<tr>
			<th>num</th>
			<th>title</th>
			<th>startdate</th>
			<th>enddate</th>
			<th>type</th>
			<th>isVote</th>
		</tr>
		<%
			for(Question q : list) {
		%>
				<tr>
					<td><%=q.getNum()%></td>
					<td><%=q.getTitle()%></td>
					<td><%=q.getStartdate()%></td>
					<td><%=q.getEnddate()%></td>
					<td><%=q.getType()%></td>
					<td>
					<%
						Date startDate = sdf.parse(q.getStartdate());
						Date endDate = sdf.parse(q.getEnddate());
						if(today.before(startDate)) {
					%>
							투표 시작 전
					<%
						} else if(today.after(endDate)) {
					%>
							투표 종료
					<%
						} else {
					%>
							<a href="">투표 하기</a>
					<%
						}
					%>
					</td>
				</tr>
		<%
			}
		%>
	</table>
	
	<!-- 페이징 -->
	<%
		if(currentPage > 1) {
	%>
			<a href="/poll/pollList.jsp?currentPage=<%=currentPage - 1%>">[이전]</a>
	<%
		}
	%>
			<%=currentPage%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/poll/pollList.jsp?currentPage=<%=currentPage + 1%>">[다음]</a>
	<%
		}
	%>
<!-- foreach문 ArrayList<Question> list 출력 title 
링크(startdate <= 오늘날짜 <= enddate) 투표시작전, 투표종료, 투표하기 -->
<!-- 번호|제목|기간|복수투표|투표(종료, 시작전)|삭제|수정|종료일자수정|결과 -->
<!-- 삭제:deletePoll 투표가 한명도 참여 없으면 -->
<!-- 전체 수정 : updatePollForm -> updatePollAction : update question, delete item, insert item-->
<!-- 종료날짜수정:updateQuestionEnddateForm -> updateQuestionEnddateAction 종료날짜가 지나지 않았으면 수정가능(오늘 이전은 수정 안됨) -->	

	
</body>
</html>
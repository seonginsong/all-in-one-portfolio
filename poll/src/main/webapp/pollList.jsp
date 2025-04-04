<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.*"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Calendar" %>
<%
	// question 테이블 리스트 -> 페이징 -> title 링크(stardate <= 오늘 날짜 <= enddate) -> 투표 프로그램
	
	// 현재 페이지 설정
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	// 페이징 정보 설정
	int rowPerPage = 10; // 한 페이지 당 보여줄 행 개수
	Paging paging = new Paging();
	paging.setCurrentPage(currentPage);
	paging.setRowPerPage(rowPerPage);
	
	// Dao 객체 생성
	QuestionDao questionDao = new QuestionDao();
	ItemDao itemDao = new ItemDao();
	// 마지막 페이지 설정
	int lastPage = paging.getLastPage((questionDao.getTotalQuestion()));
	
	// 오늘 날짜 가져오기
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String todayStr = sdf.format(date);
	Date today = sdf.parse(todayStr);
	
	// 해당 페이지에 속한 리스트 조회
	ArrayList<Question> list = questionDao.selectQuestionList(paging);
	System.out.println(today);
	
	/*강사님
	QuestionDao questiondao = new QuestionDao(); 
	ArrayList<HashMap<String, Object>> list1 = questiondao.selectQuestionList1();
	*/
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>pollList</title>
	<!-- CSS 스타일 정의
	<style>
		th {color:orange;} 
		#one {color:red;} - id
		.two {color:green;} - class
		.three {background-color: powderblue;} - class는 두개이상 설정가능 ex) class="two three"
	</style>
	 -->
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1>설문 리스트</h1>
	<table class="table table-striped table-bordered">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>시작일</th>
			<th>종료일</th><!-- 투표현황(몇명 추가하기) -->
			<th>투표현황(타입)</th>
			<th>투표</th>
			<th>삭제</th>
			<th>수정</th>
			<th>종료일수정</th>
			<th>결과</th>
		</tr>
		<%	
			
			for(Question q : list) {
				int totalCount = itemDao.getTotalCountByQnum(q.getNum());
		%>
				<tr>
					<td><%=q.getNum()%></td>
					<td><%=q.getTitle()%></td>
					<td><%=q.getStartdate()%></td>
					<td><%=q.getEnddate()%></td>
					<td><%=totalCount%>(
					<%
						if(q.getType() == 1) {
					%>
							복수투표가능
					<%
						} else {
					%>
							복수투표불가	
					<%
						}
					%>)
					</td>
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
							<a href="/poll/updateItemForm.jsp?qnum=<%=q.getNum()%>">투표 하기</a>
					<%
						}
					%>
					</td>
					<td>
                    <%
                        if (totalCount == 0) {
                    %>
                            <a href="/poll/deletePollAction.jsp?num=<%=q.getNum()%>" class="btn btn-danger">삭제</a>
                    <%
                        } else {
                    %>
                            삭제 불가(투표인원존재)
                    <%
                        }
                    %>
                    </td>
					<td><a href="/poll/updatePollForm.jsp?qnum=<%=q.getNum()%>">수정하기</a></td>
					<td>
					<%
						if(endDate.before(today)) {
					%>
							종료일 수정 불가(투표종료)
					<%
						} else {
					%>
							<form action="/poll/updateEnddate.jsp?num=<%=q.getNum()%>">
							<input type="hidden" name = "num" value="<%=q.getNum()%>">
							<input type="date" name = "enddate" value="<%=q.getEnddate()%>">
							<button type="submit">수정하기</button>
							</form>
					<%
						}
					%>
					</td>
					<td>
					<%
						if(endDate.after(today) && startDate.before(today)) {
					%>
							투표 종료x
					<%
						} else if(startDate.after(today)){
					%>
							투표 시작x
					<%
						} else {
					%>
							<a href="/poll/questionOneResult.jsp?qnum=<%=q.getNum()%>" class="two three">결과보기</a>
					<%
						}
					%>
					</td>
				</tr>
		<%
			}
		%>
	<%
	/* 수정
			Calendar today1 = Calendar.getInstance();
			int year = today1.get(Calendar.YEAR);
			int month = today1.get(Calendar.MONTH)+1;
			int date1 = today1.get(Calendar.DATE);
			
			String strToday = year+"-";
			if(month<10) {
				strToday = strToday + "0" + month + "-";
			} else {
				strToday = strToday + month;
			}
			if(date1<10) {
				strToday = strToday + "0" + date1 + "-";
			} else {
				strToday = strToday + date1;
			}
			
		for(HashMap<String, Object> m : list1) {
			String startdate = (String)m.get("startdate");
			String enddate = (String)m.get("enddate");
	%
			<tr>
			<td><%=m.get("num")%</td>
			<td><%=m.get("title")%</td>
			<td><%=m.get("startdate")%</td>
			<td><%=m.get("enddate")%</td>
			<th>
				<%	
					오늘날짜 빼기 시작날짜 : 양수(+) && 끝날짜 - 오늘날짜 : 양수(+) 
					if(strToday.compareTo(startdate) < 0) {
				%	
						투표전	
				<%		
					} else if(strToday.compareTo(enddate) > 0) {
				%
						투표종료
				<%
					} else {
				%
						<a href"">투표가능</a>
				<%
					}
				%
			</th>
			<td><%if((Integer)(m.get("cnt")) > 0) {
				%
						삭제불가
				<%
					} else {
						<a href="">삭제</a>
					}
			</td>
			<td><%if((Integer)(m.get("cnt")) > 0) {
				%
						수정불가
				<%
					} else {
						<a href="">수정</a>
					}
			</td>
			<td>
			<%
				if(enddate.compareTo(strToday) >= 0) {
			%
				<a href="">종료일 수정</a>
			<%
				} else {
			%
				종료일 수정 불가
			<%
				}
			%
			</td>
			<td>
			<%
			if(strToday.compareTo(enddate) > 0) {
		%
			<a href="">결과보기</a>
		<%
			} else {
		%
			투표진행중
		<%
			}
		%
			</td>
			</tr>
		}
	
	
	*/	
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
			<%=currentPage%>/<%=lastPage%>
	<%
		if(currentPage < lastPage) {
	%>
			<a href="/poll/pollList.jsp?currentPage=<%=currentPage + 1%>">[다음]</a>
	<%
		}
	%>
	<a href="/poll/insertPollForm.jsp">설문작성</a>
<!-- 번호|제목|기간|복수투표|투표(종료, 시작전)|삭제|수정|종료일자수정|결과 -->
<!-- 삭제:deletePoll 투표가 한명도 참여 없으면 -->
<!-- 전체 수정 : updatePollForm -> updatePollAction : update question, delete item, insert item-->
<!-- 종료날짜수정:updateQuestionEnddateForm -> updateQuestionEnddateAction 종료날짜가 지나지 않았으면 수정가능(오늘 이전은 수정 안됨) -->	

	
</body>
</html>
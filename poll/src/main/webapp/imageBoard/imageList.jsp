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
	ImageDao ImageDao = new ImageDao();
	ItemDao itemDao = new ItemDao();
	// 마지막 페이지 설정
	int lastPage = paging.getLastPage((ImageDao.getTotalImage()));
	
	// 해당 페이지에 속한 리스트 조회
	ArrayList<Image> list = ImageDao.selectImageList(paging);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	body {
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
		background-color: #f9f9f9;
		margin: 0;
		padding: 20px;
	}
	.container {
		max-width: 900px;
		margin: 0 auto;
	}
	.image-card {
		background-color: #fff;
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		margin-bottom: 20px;
		overflow: hidden;
	}
	.image-card img {
		width: 100%;
		height: 200px;
		object-fit: contain;
		background-color: #eee;
	}
	.image-card .memo {
		padding: 15px;
		font-size: 16px;
		font-weight: bold;
		color: #333;
	}
	.image-card .actions {
		padding: 10px 15px;
		background-color: #fafafa;
		text-align: right;
	}
	.image-card .actions a {
		color: #d9534f;
		text-decoration: none;
		font-weight: bold;
	}
	.pagination {
		text-align: center;
		margin-top: 30px;
	}
	.pagination a {
		margin: 0 10px;
		text-decoration: none;
		color: #007bff;
		font-weight: bold;
	}
	.pagination span {
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="container">
		<div>
			<jsp:include page="/inc/nav.jsp"></jsp:include>
		</div>
		<%
			for(Image i : list) {
		%>		
			<div class="image-card">
				<div class="memo"><%=i.getMemo()%></div>
				<img src="/poll/upload/<%=i.getFilename()%>">
				<div class="actions">
					<a href="/poll/imageBoard/deleteImage.jsp?num=<%=i.getNum()%>&filename=<%=i.getFilename()%>">삭제</a>
				</div>
			</div>
		<%
			}
		%>
		<div class="pagination">
			<%
				if(currentPage > 1) {
			%>
					<a href="/poll/imageBoard/imageList.jsp?currentPage=<%=currentPage - 1%>">[이전]</a>
			<%
				}
			%>
				<span><%=currentPage%>/<%=lastPage%></span>
			<%
				if(currentPage < lastPage) {
			%>
					<a href="/poll/imageBoard/imageList.jsp?currentPage=<%=currentPage + 1%>">[다음]</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav>
	<a href="<%=request.getContextPath()%>/index">홈으로</a>
	<a href="<%=request.getContextPath()%>/logout">로그아웃</a>
	<a href="<%=request.getContextPath()%>/updateAdminPw">비밀번호 변경</a>
	|
	<a href="<%=request.getContextPath()%>/categoryList">카테고리리스트</a>
	<a href="<%=request.getContextPath()%>/insertCategory">카테고리추가</a>
	|
	<a href="<%=request.getContextPath()%>/monthList">월별 리스트</a>
	<a href="<%=request.getContextPath()%>/insertCashDirect">Cash 추가</a>
	|
	<a href="<%=request.getContextPath()%>/statistics">통계</a>
</nav>
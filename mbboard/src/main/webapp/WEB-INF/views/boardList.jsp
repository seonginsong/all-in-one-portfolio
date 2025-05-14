<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>boardList</h1>
	<form id="boardList" action="/boardList" method="get">
	<div>
		<input type="text" id="searchWord" name="searchWord" value="${p.searchWord}">
		<button type="submit" id="swBtn">검색</button>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>no</th>
				<th>title</th>
				<th>user</th>
			</tr>
		</thead>
		<tbody id="body">
			<c:forEach var="b" items="${list}">
				<tr>
					<td>${b.boardNo}</td>
					<td><a href="/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</td>
					<td>${b.boardUser}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="/addBoard">추가</a>
	</div>
	<div>
		<c:if test='${p.currentPage>1}'>
		<a href="/boardList?currentPage=1<c:if test='${not empty param.searchWord}'>&searchWord=${param.searchWord}</c:if>">처음</a>
		</c:if>
	
		<c:if test='${p.currentPage>1}'>
		<a href="/boardList?currentPage=${p.currentPage - 1}<c:if test='${not empty param.searchWord}'>&searchWord=${param.searchWord}</c:if>">이전</a>
		</c:if>
		
		${p.currentPage}/${p.getLastPage()}
		
		<a href="/boardList?currentPage=${p.currentPage + 1}<c:if test='${not empty param.searchWord}'>&searchWord=${param.searchWord}</c:if>">다음</a>
		
		<c:if test='${p.currentPage<p.getLastPage()}'>
		<a href="/boardList?currentPage=${p.getLastPage()}<c:if test='${not empty param.searchWord}'>&searchWord=${param.searchWord}</c:if>">마지막</a>
		</c:if>
	</div>
	</form>
	
</body>
</html>
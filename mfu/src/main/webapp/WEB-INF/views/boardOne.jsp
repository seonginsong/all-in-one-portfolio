<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="css/boardOne.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardOne</title>
</head>
<body>
    <h1>BoardOne</h1>
    <table>
        <thead>
            <tr>
                <th>파일번호</th>
                <th>파일명</th>
                <th>파일유형</th>
                <th>미리보기</th>
                <th>다운로드</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${list}">
                <tr>
                    <td>${f.boardfileNo}</td>
                    <td> 
                        <c:set var="originalName" value="${fn:substringAfter(f.filename, '__')}" />
                        ${originalName}
                    </td>
                    <td>${f.filetype}</td>
                    <td>
                        <c:choose>
                            <c:when test="${f.filetype.startsWith('image/')}">
                                <img src="upload/${f.filename}" alt="이미지">
                            </c:when>
                            <c:when test="${f.filetype.startsWith('video/')}">
                                <video controls>
                                    <source src="upload/${f.filename}" type="${f.filetype}">
                                </video>
                            </c:when>
                            <c:otherwise>
                                파일 미리보기 없음
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="upload/${f.filename}" download>다운로드</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div style="text-align: center;">
        <a href="/boardList">리스트로 돌아가기</a>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="css/boardList.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList</title>
</head>
<body>
    <h1>BoardList</h1>
    <table>
        <thead>
            <tr>
                <th style="text-align: center;">번호</th>
                <th style="text-align: center;">제목</th>
            </tr>
        </thead>
        <tbody id="boardList">
            <c:forEach var="b" items="${list}">
                <tr data-board-no="${b.boardNo}">
                    <td style="text-align: center;">${b.boardNo}</td>
                    <td style="text-align: center;">${b.boardTitle}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div style="text-align: center;">
        <a href="/addBoard">추가하기</a>
    </div>
    <script>
        document.querySelector('#boardList').addEventListener('click', (e) => {
            const tr = e.target.closest('tr');
            if (tr) {
                const boardNo = tr.dataset.boardNo;
                location.href = 'boardOne?boardNo=' + boardNo;
            }    
        });
    </script>
</body>
</html>

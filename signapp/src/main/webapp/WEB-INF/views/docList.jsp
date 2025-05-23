<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>문서 목록</title>
</head>
<body>
    <h2>문서 목록</h2>
    <table border="1">
        <tr>
            <th>문서 번호</th>
            <th>제목</th>
            <th>작성자</th>
         
        </tr>
        <c:forEach var="doc" items="${documentList}">
            <tr>
                <td>${doc.documentNo}</td>
                <td>${doc.documentTitle}</td>
                <td>${doc.employeeName}</td>
                
            </tr>
        </c:forEach>
    </table>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>문서 작성</title>
</head>
<body>
    <h2>문서 작성</h2>
    <p>작성자: ${sessionScope.loginName}</p>

    <form:form method="post" action="/writeDocument" modelAttribute="document">
        제목: <form:input path="documentTitle" /><br/>
        내용: <form:textarea path="documentContent" /><br/>
        <input type="submit" value="작성하기"/>
    </form:form>
</body>
</html>

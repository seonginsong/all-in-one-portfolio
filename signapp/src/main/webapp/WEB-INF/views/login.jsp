<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
   
          <!-- 로그인 상태일 때 -->
<c:if test="${not empty loginEmployee}">
    <h2 class="mb-4">${loginEmployee.employeeName}님, 환영합니다!</h2>
    <a href="logout" class="btn btn-danger">로그아웃</a>
    
    <a href="/docView">기안84</a>
    <a href="/docWrtie">작성하기</a>
    <a href="/doc">작성하기</a>
</c:if>

<!-- 비로그인 상태일 때 -->
<c:if test="${empty loginEmployee}">
    <h2 class="mb-4">로그인</h2>
    <form action="login" method="post">
        <div class="mb-3">
            <label for="employeeName" class="form-label">이름</label>
            <input type="text" class="form-control" id="employeeName" name="employeeName" required>
        </div>
        <div class="mb-3">
            <label for="employeePw" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="employeePw" name="employeePw" required>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <button type="submit" class="btn btn-primary">로그인</button>
        <a href="joinEmployee" class="btn btn-secondary">회원가입</a>
    </form>
</c:if>
	
    </div>
</body>
</html>

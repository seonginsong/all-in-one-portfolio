<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="mb-4">회원가입</h2>
        <form action="joinEmployee" method="post">
       
            <div class="mb-3">
                <label for="employeeName" class="form-label">이름</label>
                <input type="text" class="form-control" id="employeeName" name="employeeName" required>
            </div>
            <div class="mb-3">
                <label for="employeePw" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="employeePw" name="employeePw" required>
            </div>
           <div class="mb-3">
    <label for="employeeLevel" class="form-label">직급</label>
    <select class="form-select" id="employeeLevel" name="employeeLevel" required>
        <option value="">-- 선택하세요 --</option>
        <option value="level1">level1</option>
        <option value="level2">level2</option>
        <option value="level3">level3</option>
    </select>
</div>
            <button type="submit" class="btn btn-success">회원가입</button>
            <a href="login" class="btn btn-secondary">취소</a>
        </form>
    </div>
</body>
</html>

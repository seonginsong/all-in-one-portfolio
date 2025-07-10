<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>소셜 로그인 버튼</title>
  <style>
    body {
      font-family: "Segoe UI", sans-serif;
      background: #f5f5f5;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .social-buttons {
      width: 300px;
    }

    .btn {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 12px;
      border: none;
      border-radius: 5px;
      margin-bottom: 10px;
      font-size: 16px;
      cursor: pointer;
      font-weight: 500;
      text-decoration: none;
      gap: 10px;
    }

	.btn img {
		width: 20px;
		height: 20px;
	}
    .google-btn {
      border: 1px solid #ccc;
      background: #fff;
      color: #000;
    }

    .kakao-btn {
      background: #FEE500;
      color: #000;
    }

    .naver-btn {
      background: #03C75A;
      color: #fff;
    }
  </style>
</head>
<body>
  <div class="social-buttons">
    <!-- Google -->
    <a href="/image/google.png" class="btn google-btn">
      <img src="/image/google.png" alt="Google 로고">
      Google로 시작하기
    </a>

    <!-- Kakao (이미지 URL 그대로 사용 가능) -->
    <a href="/oauth2/authorization/kakao" class="btn kakao-btn">
      <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" alt="Kakao 로고">
      카카오로 시작하기
    </a>

    <!-- Naver -->
    <a href="/oauth2/authorization/naver" class="btn naver-btn">
      <img src="/image/naver.png" alt="Naver 로고">
      네이버로 시작하기
    </a>
  </div>
</body>
</html>

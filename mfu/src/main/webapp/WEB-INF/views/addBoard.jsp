<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addBoard.css">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>addBoard</title>
</head>
<body>
    <h1>addBoard</h1>
    <!-- 게시글 작성 폼 -->
    <form id="addForm" action="${pageContext.request.contextPath}/addBoard" method="post" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td>boardtitle</td>
                <td>
                    <input type="text" name="boardTitle" id="boardTitle">
                </td>
            </tr>
            <tr>
                <td>boardfile</td>
                <td>
                    <div>
                        <!-- 파일 추가 버튼 -->
                        <button type="button" id="appendFile">파일추가</button>
                    </div>
                    <div id="fileDiv">
                        <!-- 첫 번째 파일 입력 필드 -->
                        <input type="file" name="boardfile" class="boardfile">
                    </div>
                </td>
            </tr>
        </table>
        <!-- 폼 제출 버튼 -->
        <div style="text-align: center;">
        <button type="button" id="addBtn">입력</button>&nbsp;<button type="button" id="goList">리스트로 돌아가기</button>
        </div>
    </form>
    <script>
        let flag = false;

        // '파일 추가' 버튼 클릭 시
        document.querySelector('#appendFile').addEventListener('click', () => {
            flag = false; // 두번째, 세번째 flag=false 초기화 후..
            
            // 파일 입력 필드에 값이 비어있는지 확인
            let boardfileList = document.querySelectorAll('.boardfile');
            boardfileList.forEach((e) => {
                if(e.value == '') {
                    alert('공백있다');
                    flag = true; // 공백이 존재한다면
                    return; // forEach 콜백함수를 탈출
                }
            });

            // 공백이 있다면 함수 종료
            if(flag) {
                return;
            }

            // 새로운 파일 입력 필드 추가
            let inputFile = document.createElement('input');
            inputFile.setAttribute('type', 'file');
            inputFile.setAttribute('name', 'boardfile');
            inputFile.setAttribute('class', 'boardfile');

            // 파일 입력 필드 추가
            document.querySelector('#fileDiv').appendChild(inputFile);
        });

        // '입력' 버튼 클릭 시
        document.querySelector('#addBtn').addEventListener('click', () => {
            // 제목을 입력했는지 확인
            if(document.querySelector('#boardTitle').value == '') {
                alert('title을 입력하세여');
                return;
            }

            // 추가되지 않은 파일에 대한 node(input type=file)을 삭제
            let boardfileList = document.querySelectorAll('.boardfile');
            boardfileList.forEach((e) => {
                if(e.value == '') {
                    e.remove(); // node 삭제
                }
            });

            // 폼 제출
            document.querySelector('#addForm').submit();
        });
        // 리스트
           document.querySelector('#goList').addEventListener('click', () => {
        	   location.href = '/boardList';
            });
    </script>
</body>
</html>

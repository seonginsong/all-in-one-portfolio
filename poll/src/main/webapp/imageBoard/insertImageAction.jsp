<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.nio.file.*" %>
<%@ page import="dto.*" %>
<%@ page import="model.*" %>
<%
	String memo = request.getParameter("memo");	
	System.out.println(memo);
	
	Part part = request.getPart("imageFile"); // 파일 받는 API
	String originalName = part.getSubmittedFileName(); // one1.png
	System.out.println("originalName : "+originalName);
	
	// 1) 중복되지 않는 새로운 파일이름 생성 - java.util.UUID API 사용
	UUID uuid = UUID.randomUUID();
	String filename = uuid.toString();
	filename = filename.replace("-", "");
	System.out.println("uuid str : "+filename);
	
	// 2) 1의 결과에 확장자 추가
	int dotLastPos = originalName.lastIndexOf("."); // 마지막 . 위치의 인덱스값 반환
	System.out.println("dotLastPos : "+dotLastPos);
	
	// request입력값 유효성 검정
	String ext = originalName.substring(dotLastPos);
	if(!ext.equals(".png")) {
		response.sendRedirect("/poll/imageBoard/insertImageForm.jsp?msg=ErrorNotPng");
		return; // jsp 코드 진행을 종료
	}
	
	filename = filename + originalName.substring(dotLastPos);
	System.out.println("filename: "+filename);
	
	Image img = new Image();
	img.setMemo(memo);
	img.setFilename(filename);
	
	// 3) 파일저장
	// 빈파일 생성
	String path = request.getServletContext().getRealPath("upload"); // getS~ : 톰캣안에 poll 프로젝트 안에 upload폴더의 , getReal~ : 실제 물리적 주소를 반환
	System.out.println("path : " +path);
	File emptyFile = new File(path, filename);
	// 파일을 보낼 inputstream 설정
	InputStream is = part.getInputStream(); // 파트안의 스트림(이미지파일의 바이너리파일)
	// 파일을 받을 outputstream을 설정
	OutputStream os = Files.newOutputStream(emptyFile.toPath());
	// is에 있는 내용을 os로 흘려보내기
	is.transferTo(os); // inputstream binary를 반복시켜서(1byte씩) outputstream으로 이동
	
	// 4) db에 저장
	ImageDao imageDao = new ImageDao();
	imageDao.insertImage(img);
	response.sendRedirect("/poll/imageBoard/imageList.jsp");
	
%>
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.UUID;

import dto.Receit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.ReceitDao;


@WebServlet("/insertReceit")
public class InsertReceitController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));	
		System.out.println(cashNo);
		
		ReceitDao receitDao = new ReceitDao();
		int cnt=0;
		try {
			cnt = receitDao.selectReceitCount(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cnt>0) {
			response.sendRedirect(request.getContextPath()+"/cashOne?cashNo="+cashNo+"&cmsg=error");
			return;
		}
		request.setAttribute("cashNo", cashNo);
		request.getRequestDispatcher("/WEB-INF/view/insertReceit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));	
		System.out.println(cashNo);
		
		// 이미 존재할경우 확인
		ReceitDao receitDao = new ReceitDao();
		int cnt=0;
		try {
			cnt = receitDao.selectReceitCount(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Part part = request.getPart("receitFile"); // 파일 받는 API
		String originalName = part.getSubmittedFileName(); // one1.png
		System.out.println("originalName : "+originalName);
		
		if(request.getPart("receitFile") == null || cnt>0) {
			response.sendRedirect(request.getContextPath()+"/cashOne?cashNo="+cashNo+"&nmsg=error");
			System.out.println(cnt);
		} else {
		
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
		if(!ext.equals(".png") && !ext.equals(".jpg")) {
			response.sendRedirect(request.getContextPath()+"/insertReceit?cashNo=" + cashNo + "msg=ErrorNotExt");
			return; // jsp 코드 진행을 종료
		}
		
		filename = filename + originalName.substring(dotLastPos);
		System.out.println("filename: "+filename);
		
		Receit receit = new Receit();
		receit.setCashNo(cashNo);
		receit.setFilename(filename);
		
		// 3) 파일저장
		// 빈파일 생성
		String path = request.getServletContext().getRealPath("upload"); // getS~ : 톰캣안에 cashbook 프로젝트 안에 upload폴더의 , getReal~ : 실제 물리적 주소를 반환
		System.out.println("path : " +path);
		File emptyFile = new File(path, filename);
		// 파일을 보낼 inputstream 설정
		InputStream is = part.getInputStream(); // 파트안의 스트림(이미지파일의 바이너리파일)
		// 파일을 받을 outputstream을 설정
		OutputStream os = Files.newOutputStream(emptyFile.toPath());
		// is에 있는 내용을 os로 흘려보내기
		is.transferTo(os); // inputstream binary를 반복시켜서(1byte씩) outputstream으로 이동
		
		// 4) db에 저장
		
			try {
				receitDao.insertReceit(receit);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/cashOne?cashNo="+cashNo);
	}
	}
}

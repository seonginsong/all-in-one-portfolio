package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReceitDao;


@WebServlet("/deleteReceit")
public class DeleteReceitController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		String filename = request.getParameter("filename");
		System.out.println(request.getParameter("cashNo"));
		System.out.println(request.getParameter("filename"));
		
		// db 삭제
		ReceitDao receitDao = new ReceitDao();
		try {
			receitDao.deleteReceit(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 파일 삭제
		String path = request.getServletContext().getRealPath("upload");
		File file = new File(path, filename); // new File 경로에 파일이 없으면 빈파일을 생성
		if(file.exists()) { // 빈파일이 아니라면
			file.delete(); // 삭제
		}
		response.sendRedirect(request.getContextPath()+"/cashOne?cashNo="+cashNo);
	}

}

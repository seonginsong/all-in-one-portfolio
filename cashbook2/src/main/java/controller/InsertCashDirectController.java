package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CategoryDao;


@WebServlet("/insertCashDirect")
public class InsertCashDirectController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		// insertCashForm.jsp -> kind 선택(String kind)
		String kind = request.getParameter("kind");
		// insertCashForm.jsp에서 kind 선택 후 재요청 
		// DB : 선택된 kind의 title 목록
		
		// 카테고리 목록 뽑기
		ArrayList<Category> list = null;
		if(kind != null) { // insertCashForm.jsp에서 kind 선택 후 재요청
			// DB : 선택된 kind의 title 목록
			CategoryDao categoryDao = new CategoryDao();
			try {
				list = categoryDao.selectCategoryListByKind(kind);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("kind", kind);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/view/insertCashDirect.jsp").forward(request, response);
	}


}

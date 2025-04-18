package controller;

import java.io.IOException;
import java.sql.SQLException;

import dto.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CategoryDao;


@WebServlet("/insertCategory")
public class InsertCategoryController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/insertCategory.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kind = request.getParameter("kind");
		String title = request.getParameter("title");
		if(request.getParameter("kind") == null || request.getParameter("title").equals("")) {
			response.sendRedirect(request.getContextPath()+"/insertCategory?msg=NoInsert");
			return;
		}
		
		CategoryDao categoryDao = new CategoryDao();
		int ckCnt = 0;
		try {
			ckCnt = categoryDao.selectCntKindTitle(title, kind);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(ckCnt > 0) {
			response.sendRedirect(request.getContextPath()+"/insertCategory?umsg=SameTitle");
		} else {
			Category c = new Category();
			c.setKind(kind);
			c.setTitle(title);
			
			try {
				categoryDao.insertCategory(c);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/categoryList");
		}
	}

}

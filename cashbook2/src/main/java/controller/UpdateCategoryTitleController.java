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


@WebServlet("/updateCategoryTitle")
public class UpdateCategoryTitleController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		
		
		int cgno = Integer.parseInt(request.getParameter("categoryNo"));
		
		CategoryDao categoryDao = new CategoryDao();
		Category c = new Category();
		
		try {
			c = categoryDao.selectCategoryOne(cgno);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("cgno", cgno);
		request.setAttribute("title", c.getTitle());
		request.getRequestDispatcher("/WEB-INF/view/updateCategoryTitle.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		String title = request.getParameter("title");
		if(request.getParameter("title") == null || request.getParameter("title").equals("")) {
			response.sendRedirect(request.getContextPath()+"/updateCategoryTitle?msg=NoInsert&categoryNo="+categoryNo);
			return;
		}
		CategoryDao categoryDao = new CategoryDao();
		Category c = new Category();
		try {
			c = categoryDao.selectCategoryOne(categoryNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ckCnt = 0;
		try {
			ckCnt = categoryDao.selectCntKindTitle(title, c.getKind());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ckCnt);
		if(ckCnt > 0) {
			response.sendRedirect(request.getContextPath()+"/updateCategoryTitle?umsg=SameTitle&categoryNo="+categoryNo);
		} else {
			try {
				categoryDao.updateCategoryTitle(title, categoryNo);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/categoryList");
		}
	}

}

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;
import dto.Paging;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CategoryDao;


@WebServlet("/categoryList")
public class CategoryListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		
		String searchWord = "";
		if(request.getParameter("searchWord") != null) {
			searchWord = request.getParameter("searchWord");
		}
		
		String kind = "";
		String strKind = request.getParameter("kind");
		if(strKind != null && !request.getParameter("kind").equals("전체")) {
			kind = request.getParameter("kind");
		}
		// 페이징
		int currentPage = 1;
		int rowPerPage = 10;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		Paging p = new Paging();
		p.setCurrentPage(currentPage);
		p.setRowPerPage(rowPerPage);
			
		CategoryDao categoryDao = new CategoryDao();
		
		int totalRow = 0;
		try {
			totalRow = categoryDao.getTotalCategory(searchWord, kind);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int lastPage = p.getLastPage(totalRow);

		ArrayList<Category> list = null;
		
		try {
			list = categoryDao.selectCategoryList(p, searchWord, kind);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("kind", kind);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/WEB-INF/view/categoryList.jsp").forward(request, response);
		
	}
}

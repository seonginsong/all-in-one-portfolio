package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CashDao;
import model.CategoryDao;

@WebServlet("/deleteCategory")
public class DeleteCategoryController extends HttpServlet {

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
		
		CashDao cashDao = new CashDao();
		int cnt = 0;
		try {
			cnt = cashDao.selectCountCash(cgno);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cnt);
		if(cnt == 0) {
			try {
				categoryDao.deleteCategory(cgno);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/categoryList");
		} else {
			response.sendRedirect(request.getContextPath()+"/categoryList?msg=CashIsExist&cgno="+cgno);
		}
	}

}

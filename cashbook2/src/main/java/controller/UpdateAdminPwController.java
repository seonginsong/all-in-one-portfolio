package controller;

import java.io.IOException;
import java.sql.SQLException;

import dto.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdminDao;


@WebServlet("/updateAdminPw")
public class UpdateAdminPwController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		request.setAttribute("adminId", adminId);
		request.getRequestDispatcher("/WEB-INF/view/updateAdminPw.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");
		
		String nowPw = request.getParameter("nowPw");
		String nextPw1 = request.getParameter("nextPw1");
		String nextPw2 = request.getParameter("nextPw2");
		
		AdminDao adminDao = new AdminDao();
		Admin a = null;
		try {
			a = adminDao.selectAdmin(adminId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(nowPw.equals(a.getAdminPw()) && nextPw1.equals(nextPw2) && !nowPw.equals(nextPw1) && request.getParameter("nextPw1") != "" && request.getParameter("nextPw2") != null) {
			try {
				adminDao.updatePw(nextPw1, adminId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/login");
		} else {
			response.sendRedirect(request.getContextPath()+"/updateAdminPw?msg=PasswordError");
		}
	}

}

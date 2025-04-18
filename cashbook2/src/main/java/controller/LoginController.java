package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdminDao;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("adminId");
		String pw = request.getParameter("adminPw");
		
		AdminDao adminDao = new AdminDao();
		int loginOk = 0;
		try {
			loginOk = adminDao.selectLogin(id, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("loginOk :"+loginOk);
		
		if(loginOk == 1) {
			HttpSession session = request.getSession(); // ⭐️ 세션 가져오기
			session.setAttribute("loginId", id);
			response.sendRedirect(request.getContextPath()+"/index");
		} else {
			response.sendRedirect(request.getContextPath()+"/login?msg=PasswordError");
		}
	}

}

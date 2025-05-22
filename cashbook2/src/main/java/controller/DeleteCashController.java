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


@WebServlet("/deleteCash")
public class DeleteCashController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));
		int d = Integer.parseInt(request.getParameter("d"));


		CashDao cashDao = new CashDao();
		try {
			cashDao.deleteCash(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/dateList?y="+y+"&m="+m+"&d="+d);
	}


}

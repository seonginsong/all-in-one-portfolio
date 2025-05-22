package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import dto.Cash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CashDao;


@WebServlet("/dateList")
public class DateListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));
		int d = Integer.parseInt(request.getParameter("d"));
		
		String kind = "";
		String strKind = request.getParameter("kind");
		if(strKind != null && !request.getParameter("kind").equals("전체")) {
			kind = request.getParameter("kind");
		} else {
			kind = "";
		}
		
		CashDao cashDao = new CashDao();
		ArrayList<Cash> list = null;
		try {
			list = cashDao.selectCashByDate(y, m, d, kind);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum1 = 0;
		try {
			sum1 = cashDao.selectSumAmountByDate(y, m, d, "수입");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum2 = 0;
		try {
			sum2 = cashDao.selectSumAmountByDate(y, m, d, "지출");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum3 = 0;
		try {
			sum3 = cashDao.selectSumAmountByDate(y, m, d, "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//넘길 cashdate 값
		String cashdate = y+"-"+m+"-"+d;
		System.out.println("cashdate : "+cashdate);
		
		DecimalFormat df = new DecimalFormat("###,###");
		String sumPlus = df.format(sum1);
		String sumMinus = df.format(sum2);
		String sumAll = df.format(sum1-sum2);
		
		
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		request.setAttribute("d", d);
		request.setAttribute("list", list);
		request.setAttribute("sumPlus", sumPlus);
		request.setAttribute("sumMinus", sumMinus);
		request.setAttribute("sumAll", sumAll);
		request.setAttribute("cashdate", cashdate);
		request.setAttribute("kind", kind);
		request.getRequestDispatcher("/WEB-INF/view/dateList.jsp").forward(request, response);
	}


}

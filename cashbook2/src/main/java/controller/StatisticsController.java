package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import dto.Static;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.StaticDao;

@WebServlet("/statistics")
public class StatisticsController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		StaticDao staticDao = new StaticDao();
		int allPlusSum = 0;
		try {
			allPlusSum = staticDao.selectStaticAll("수입");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int allMinusSum = 0;
		try {
			allMinusSum = staticDao.selectStaticAll("지출");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("allPlusSum :"+allPlusSum);
		System.out.println("allMinusSum :"+allMinusSum);
		
		int year = 0;
		if(request.getParameter("year") == null) {
			year = 2025;
		} else {
			year = Integer.parseInt(request.getParameter("year"));
		}
		ArrayList<Static> yearList = null;
		try {
			yearList = staticDao.selectStaticByYear();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Static> monthList = null;
		try {
			monthList = staticDao.selectStaticByMonth();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Static> sList = null;
		try {
			sList = staticDao.selectStaticBySYear(year);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Integer> yearOptionList = null;
		try {
			yearOptionList = staticDao.selectDistinctYears();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int currentMonth = -1;
		int incomeMonth = 0;
		int expenseMonth = 0;

		DecimalFormat df = new DecimalFormat("###,###");

		request.setAttribute("allPlusSum", df.format(allPlusSum));
		request.setAttribute("allMinusSum", df.format(allMinusSum));
		request.setAttribute("allSum", df.format(allPlusSum-allMinusSum));
		request.setAttribute("year", year);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("incomeMonth", incomeMonth);
		request.setAttribute("expenseMonth", expenseMonth);
		request.setAttribute("year", year);
		request.setAttribute("year", year);
		request.setAttribute("year", year);
		request.setAttribute("year", year);
		request.setAttribute("year", year);
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		request.setAttribute("sList", sList);
		request.setAttribute("yearOptionList", yearOptionList);
		
		request.getRequestDispatcher("/WEB-INF/view/statistics.jsp").forward(request, response);
	}

}

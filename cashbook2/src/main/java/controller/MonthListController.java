package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import dto.Cash;
import dto.Month;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CashDao;


@WebServlet("/monthList")
public class MonthListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		// 현재 월의 기본값 설정(1일)
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);
		
		String strYear = request.getParameter("targetYear");
		String strMonth = request.getParameter("targetMonth");
		
		int year = (strYear != null) ? Integer.parseInt(strYear) : firstDate.get(Calendar.YEAR);
		int month = (strMonth != null) ? Integer.parseInt(strMonth) : firstDate.get(Calendar.MONTH);

		// Calendar 객체에 반영 (월은 0부터 시작)
		firstDate.set(Calendar.YEAR, year);
		firstDate.set(Calendar.MONTH, month);
		
		//targetMonth가 있을 경우 : 월은 targetMonth
		System.out.println("targetMonth: "+request.getParameter("targetMonth"));
		if(request.getParameter("targetMonth") != null) {
			firstDate.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth")));
		}
		
		// 이전/다음 달 계산
		Calendar prev = (Calendar) firstDate.clone();
		prev.add(Calendar.MONTH, -1);
		int prevYear = prev.get(Calendar.YEAR);
		int prevMonth = prev.get(Calendar.MONTH);

		Calendar next = (Calendar) firstDate.clone();
		next.add(Calendar.MONTH, 1);
		int nextYear = next.get(Calendar.YEAR);
		int nextMonth = next.get(Calendar.MONTH);
		
		// 달력 형식에 필요한 값들
		int lastDate = firstDate.getActualMaximum(Calendar.DATE);
		System.out.println("마지막 날짜:"+lastDate);
		
		// 요일(1 - 일 2- 월)
		int dayOfWeek = firstDate.get(Calendar.DAY_OF_WEEK);
		
		// model2 변환 위한 변수
		int nowYear = firstDate.get(Calendar.YEAR);
		int nowMonth = firstDate.get(Calendar.MONTH);
		
		// DTO
		Cash cash = new Cash();
		Month m = new Month();
		m.setLastDate(lastDate);
		m.setDayOfWeek(dayOfWeek);
		int startBlank = m.getStartBlank();
		int totalCell = m.getTotalCell();
		
		//DAO
		CashDao cashDao = new CashDao();
		ArrayList<Cash> list = null;
		try {
			list = cashDao.selectCashByMonth(firstDate.get(Calendar.YEAR), firstDate.get(Calendar.MONTH) + 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("nowYear", nowYear);
		request.setAttribute("nowMonth", nowMonth);
		request.setAttribute("prevYear", prevYear);
		request.setAttribute("prevMonth", prevMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.getRequestDispatcher("/WEB-INF/view/monthList.jsp").forward(request, response);
	}

}

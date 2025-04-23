package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import dto.Cash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CashDao;
import model.ReceitDao;


@WebServlet("/cashOne")
public class CashOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		// cash 목록 뽑기
		Cash c = new Cash();
		CashDao cashDao = new CashDao();
		try {
			c = cashDao.selectCashOne(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// receit 있는지 확인
		ReceitDao receitDao = new ReceitDao();
		int cnt = 0;
		try {
			cnt = receitDao.selectReceitCount(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filename = null;
		try {
			filename = receitDao.selectReceitFilename(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 금액 형식
		DecimalFormat df = new DecimalFormat("###,###");
		
		String arr[] = c.getCashDate().split("-");
		
		System.out.println(arr[0]+arr[1]+arr[2]);
		int y = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int d = Integer.parseInt(arr[2]);
		
		request.setAttribute("cashNo", c.getCashNo());
		request.setAttribute("kind", c.getKind());
		request.setAttribute("title", c.getTitle());
		request.setAttribute("cashDate", c.getCashDate());
		request.setAttribute("amount", df.format(c.getAmount()));
		request.setAttribute("memo", c.getMemo());
		request.setAttribute("color", c.getColor());
		request.setAttribute("cnt", cnt);
		request.setAttribute("filename", filename);
		
		request.getRequestDispatcher("/WEB-INF/view/cashOne.jsp").forward(request, response);
	}

}

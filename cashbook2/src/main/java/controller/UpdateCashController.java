package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Cash;
import dto.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CashDao;
import model.CategoryDao;

@WebServlet("/updateCash")
public class UpdateCashController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminId = (String)session.getAttribute("loginId");

		if(adminId == null) { //로그아웃 상태라면
			response.sendRedirect(request.getContextPath()+"/login");
			//로그인 페이지로 리다이렉트
			return;
		}
		
		String kind = request.getParameter("kind");
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));

		
		Cash cs = new Cash();
		CashDao cashDao = new CashDao();
		try {
			cs = cashDao.selectCashOne(cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 카테고리 목록 뽑기
		ArrayList<Category> list = null;
		if(kind != null) { // insertCashForm.jsp에서 kind 선택 후 재요청
			// DB : 선택된 kind의 title 목록
			CategoryDao categoryDao = new CategoryDao();
			try {
				list = categoryDao.selectCategoryListByKind(kind);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			CategoryDao categoryDao = new CategoryDao();
			try {
				list = categoryDao.selectCategoryListByKind(cs.getKind());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int y = 0, m = 0, d = 0;
		try {
			y = Integer.parseInt(request.getParameter("y"));
			m = Integer.parseInt(request.getParameter("m"));
			d = Integer.parseInt(request.getParameter("d"));
		} catch(NumberFormatException e) {
			// 파라미터가 없을 때는 cashDate에서 파싱해서 설정
			String[] dateParts = cs.getCashDate().split("-");
			y = Integer.parseInt(dateParts[0]);
			m = Integer.parseInt(dateParts[1]);
			d = Integer.parseInt(dateParts[2]);
		}

		request.setAttribute("y", y);
		request.setAttribute("m", m);
		request.setAttribute("d", d);
		
		
		String cashDate = request.getParameter("cashDate");
		request.setAttribute("amount", cs.getAmount());
		request.setAttribute("memo", cs.getMemo());
		request.setAttribute("color", cs.getColor());
		request.setAttribute("categoryNo", cs.getCategoryNo());
		request.setAttribute("cashDate", cs.getCashDate());
		request.setAttribute("cashNo", cashNo);
		
		request.setAttribute("kind", kind);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/updateCash.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));
		if(request.getParameter("cashDate") == null || request.getParameter("memo") == null || request.getParameter("amount") == null || request.getParameter("category_no") == null ||
		request.getParameter("cashDate").equals("") || request.getParameter("memo").equals("") || request.getParameter("amount").equals("") || request.getParameter("category_no").equals("")) {
			response.sendRedirect(request.getContextPath()+"/updateCashForm?cashNo="+cashNo+"&msg=NoInsert");
			return;
		}

		String cashDate = request.getParameter("cashDate");
		String memo = request.getParameter("memo");
		String color = request.getParameter("color");
		int amount = Integer.parseInt(request.getParameter("amount"));
	 	int categoryNo = Integer.parseInt(request.getParameter("category_no"));
		System.out.println("cashDate:"+cashDate);
		System.out.println("memo:"+memo);
		System.out.println("color:"+color);
		System.out.println("amount:"+amount);
		System.out.println("categoryNo:"+categoryNo);
		CashDao cashDao = new CashDao();
		Cash c = new Cash();
		c.setCashDate(cashDate);
		c.setAmount(amount);
		c.setColor(color);
		c.setCategoryNo(categoryNo);
		c.setMemo(memo);
		
		String arr[] = cashDate.split("-");
		
		System.out.println(arr[0]+arr[1]+arr[2]);
		int y = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int d = Integer.parseInt(arr[2]);
		
		try {
			cashDao.updateCash(categoryNo, cashDate, amount, memo, color, cashNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/dateList?y="+y+"&m="+m+"&d="+d);
		
	}

}

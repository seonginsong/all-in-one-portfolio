package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GuguModel;


@WebServlet("/gugu.goodee")
public class GuguController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Controller
		// 1) 요청 분석
		int dan = 2;
		if(request.getParameter("dan") != null) {
			dan = Integer.parseInt(request.getParameter("dan"));
		}
		// 2) 모델 연결(모델에게서 모델값을 반환)
		GuguModel guguModel = new GuguModel();
		ArrayList<String> list = guguModel.getDanList(dan);
		// 3) 뷰연결(뷰에게 모델값 전달)
		request.setAttribute("list", list);
		request.setAttribute("dan", dan);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/guguView.jsp"); // 연결뷰준비
		rd.forward(request, response);
	}
}

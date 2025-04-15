package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/index") // 웹브라우저에서 요청하는 이름
public class IndexController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("get index...");
		System.out.println(request.getParameter("name"));
		String name = request.getParameter("name");
		// 보여줄 html 파일을 만들어야함->response 객체에 페이지를 만들어야 한다
		PrintWriter out = response.getWriter();
		out.print("<html><head></head><body><h1>Hello Index Page</h1><div>" + name + "</div></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}

// JSP를 만들면 톰켓은 JSP를 이런 형태의 servlet 클래스로 변경하여 톰켓안에서 실행한다.
package com.example.mbboard.filter;

import java.io.IOException;

import com.example.mbboard.dto.Member;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpSession session = httpReq.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			// 로그인이 안되어 있을때 (인증)
			if(loginMember == null) {
				if(response instanceof HttpServletResponse) {
					log.info("OnSessionFilter에 걸려서 sendRedirect /login 됨");
					((HttpServletResponse) response).sendRedirect("/login");
					return;
				}
			}
			// 로그인은 되어있지만 ROLE이 안될때 (인가)
			if(loginMember.getMemberRole().equals("ADMIN") == false) {
				if(response instanceof HttpServletResponse) {
					log.info("OnSessionFilter에 걸려서 sendRedirect /member/info 됨");
					((HttpServletResponse) response).sendRedirect("/member/info");
					return;
				}
			}
		}

		chain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

}

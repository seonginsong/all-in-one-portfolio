package com.example.mbboard.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/a")
public class AFilter implements Filter{
	// AFilter aFilter = new AFilter(); // 변수 이름은 기본값으로 첫문자 소문자 + 나머지
	
	public AFilter () {
		log.info("AFilter 객체 생성 후 자동으로 빈 등록됨");
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//선행
		log.info("/a 선행내용");
		chain.doFilter(request, response);
		//후행
		log.info("/a 후행내용");
	}

}

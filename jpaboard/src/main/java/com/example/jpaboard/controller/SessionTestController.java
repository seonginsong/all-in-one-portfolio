package com.example.jpaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SessionTestController {
	@GetMapping("/newSession")
	public String newSession(HttpSession session) { // request.getHttpSession
		session.setAttribute("sessionData", "GDJ91");
		log.debug("sessionData : gdj91");
		return "sessionTest";
	}
	@GetMapping("/dropSession")
	public String dropSession(HttpSession session) {
		session.invalidate();
		return "sessionTest";
	}
}

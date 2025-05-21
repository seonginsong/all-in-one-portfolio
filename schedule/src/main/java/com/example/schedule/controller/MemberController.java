package com.example.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.schedule.dto.Member;
import com.example.schedule.dto.PwHistory;
import com.example.schedule.service.IMemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired IMemberService memberService;
	
	@GetMapping("/insertMember")
	public String joinMember() {
		return "insertMember";
	}
	// 회원가입
	@PostMapping("/insertMember")
	public String joinMember(Member paramMember) {
		
		Member member = new Member();
		member.setId(paramMember.getId());
		member.setPw(paramMember.getPw());
		member.setEmail(paramMember.getEmail());
		memberService.insertMember(member);
		return "redirect:/login";
	}
	
	@GetMapping({"/", "/login"})
	public String login(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/index";
		}
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member paramMember, Model model) {
		Member loginMember = memberService.login(paramMember);
		if(loginMember != null) {
			log.info("loginMember:" + loginMember.toString());
			session.setAttribute("loginMember", loginMember);
			// login_history의 logindate update
			memberService.insertLoginDate(loginMember);
			// 로그인 시 active 값을 ON으로 변경(휴면계정인 아이디가 로그인했을 경우)
			memberService.updateLoginActive(loginMember);
		} else {
			model.addAttribute("loginFail", true);
			return "login";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/index")
	public String index(HttpSession session) {
		return "index";
	}
	
	// 비밀번호
	@GetMapping("/changePw")
	public String changePw() {
		return "changePw";
	}
	
	@PostMapping("/changePw")
	public String changePw(Member member, HttpSession session) {
		log.info("changePw 호출됨: " + member.getId() + ", " + member.getPw());
		memberService.updatePw(member);
		PwHistory ph = new PwHistory();
		ph.setId(member.getId());
		ph.setPw(member.getPw());
		memberService.insertPwHistory(ph);
		session.invalidate();
		return "redirect:/login";
	}
}

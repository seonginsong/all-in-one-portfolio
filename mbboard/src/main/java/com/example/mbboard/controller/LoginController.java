package com.example.mbboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mbboard.dto.ConnectCount;
import com.example.mbboard.dto.Member;
import com.example.mbboard.service.ILoginService;
import com.example.mbboard.service.IRootService;
import com.example.mbboard.service.RootService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

    private final RootService rootService;
	@Autowired ILoginService loginService;
	@Autowired IRootService connectCount;

    LoginController(RootService rootService) {
        this.rootService = rootService;
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member paramMember) {
		Member loginMember = loginService.login(paramMember);
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			// 멤버 카운트 +1
			ConnectCount cc = new ConnectCount();
			cc.setMemberRole(loginMember.getMemberRole());
			if(rootService.getConnectDateByKey(cc) == null) {
				rootService.addConnectCount(cc);
			} else {
				rootService.modifyConnectCount(cc);
			}
		}
		return "login";
	}
	
	
}

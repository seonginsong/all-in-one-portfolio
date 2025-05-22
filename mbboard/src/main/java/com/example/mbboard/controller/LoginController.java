package com.example.mbboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mbboard.dto.ConnectCount;
import com.example.mbboard.dto.Member;
import com.example.mbboard.service.ILoginService;
import com.example.mbboard.service.IRootService;
import com.example.mbboard.service.LoginService;
import com.example.mbboard.service.RootService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

    private final LoginService loginService_1;

    private final RootService rootService;
	@Autowired ILoginService loginService;
	@Autowired IRootService connectCount;

    LoginController(RootService rootService, LoginService loginService_1) {
        this.rootService = rootService;
        this.loginService_1 = loginService_1;
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping({"/", "/login"})
	public String login(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/member/info";
		}
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(HttpSession session, Member paramMember, HttpServletResponse rsponse, Model model) {
		Member loginMember = loginService.login(paramMember);
		if(loginMember != null) {
			
			log.info(paramMember.toString());
			
			session.setAttribute("loginMember", loginMember);
			// 멤버 카운트 +1
			ConnectCount cc = new ConnectCount();
			cc.setMemberRole(loginMember.getMemberRole());
			if(rootService.getConnectDateByKey(cc) == null) {
				rootService.addConnectCount(cc);
			} else {
				rootService.modifyConnectCount(cc);
			}
			// 클라이언트 쿠키에도 로그인에 성공한 ID만 저장
			if(paramMember.getSaveIdCk() != null) {
				Cookie c = new Cookie("saveId", paramMember.getMemberId());
				rsponse.addCookie(c);
			} else {
				Cookie c = new Cookie("saveId", "");
				rsponse.addCookie(c);
			}
			
		} else {
			model.addAttribute("loginFail", true);
			return "login";
		}
		return "redirect:/member/info";
	}
	
	@GetMapping("/findMemberPw")
	public String findMemberPw() {
		return "findMemberPw";
	}
	@PostMapping("/findMemberPw")
	public String findMemberPw(Member member, Model model) {
		// 검사
		//비밀번호 변경
		Member m = loginService.selectIdEmail(member);
		if(m == null) {
			model.addAttribute("noIdEmail", true);
			return "findMemberPw";
		}
		loginService.changeMemberPwByAdmin(member);
		//메일로 전송
		//로그인페이지로 이동
		return "rechangeMemberPw";
	}
	
	@GetMapping("/rechangeMemberPw")
	public String rechangeMemberPw() {
		
		return "rechangeMemberPw";
	}
	
	@PostMapping("/rechangeMemberPw")
	public String rechangeMemberPw(@RequestParam(name = "memberId") String memberId,
									@RequestParam(name = "memberPw") String memberPw,
									@RequestParam(name = "reMemberPw") String reMemberPw) {
		int row = loginService.rechangeMemberPw(memberId, memberPw, reMemberPw);
		if(row == 0) {
			log.info("rechange fail");
		} else {
			log.info("rechange success");
		}
		return "redirect:/login";
	}
	
	@GetMapping("/modifyPw")
	public String modifyPw() {
		return "modifyPw";
	}
	
	@PostMapping("/modifyPw")
	public String modifyPw(@RequestParam(name = "memberId") String memberId
							, @RequestParam(name = "currentPw") String currentPw
							, @RequestParam(name = "newPw") String newPw
							, @RequestParam(name = "newPw2") String newPw2
							, Model model) {
		if(newPw != null && newPw2 != null && !newPw.equals(newPw2)) {
			model.addAttribute("pwNotCorrect", true);
			return "modifyPw";
		}
		int row = loginService.updateMemberPw(memberId, currentPw, newPw);
		if(newPw != null && row == 0) {
			model.addAttribute("modifyFail", true);
			return "modifyPw";
		}
		if(newPw != null && newPw.equals(currentPw)) {
			model.addAttribute("samePw", true);
			return "modifyPw";
		}
		return "redirect:/login";
	}
}

package com.example.mbboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mbboard.dto.Member;
import com.example.mbboard.service.IMemberService;

@Controller
public class MemberController {
	@Autowired IMemberService memberService;
	
	@GetMapping("/joinMember")
	public String joinMember() {
		return "joinMember";
	}
	// 회원가입
	@PostMapping("/joinMember")
	public String joinMember(Member paramMember) {
		
		Member member = new Member();
		member.setMemberId(paramMember.getMemberId());
		member.setMemberPw(paramMember.getMemberPw());
		memberService.insertMember(member);
		return "redirect:/login";
	}
	//member
	// 세션안의 상세정보를 보여주는 요청 -> 로그인 상태에서 요청가능 -> 필터1)
	@GetMapping("/member/info") 
	public String info() {
		return "/member/info";
	}
	
	@GetMapping("/member/memberHome")
	public String memberHome() {
		return "/member/memberHome";
	}
	
	// admin
	// 관리자 페이지 요청 -> 로그인 상태이고 role이 'ADMIN'요청가능 -> 필터2)
	@GetMapping("/admin/adminHome") 
	public String adminHome() {
		return "/admin/adminHome";
	}
	
	@GetMapping("/admin/memberList")
	public String memberList(Model model) {
		List<Member> list = memberService.selectMemberList();
		model.addAttribute("list", list);
		return "/admin/memberList";
	}
}

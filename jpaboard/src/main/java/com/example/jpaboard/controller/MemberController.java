package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jpaboard.dto.MemberForm;
import com.example.jpaboard.entity.Member;
import com.example.jpaboard.entity.MemberMapping;
import com.example.jpaboard.repository.MemberRepository;
import com.example.jpaboard.util.SHA256Util;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	
	// 회원가입 + member_id 중복확인
	@GetMapping("/member/joinMember")
	public String joinMember() {
		return "member/joinMember";
	}
	@PostMapping("/member/joinMember")
	public String joinMember(MemberForm memberForm, RedirectAttributes rda) {
		// id가 db에 존재한다면
		log.debug(memberForm.toString());
		log.debug("isMemberId : " + memberRepository.existsByMemberId(memberForm.getMemberId()));
		
		if(memberRepository.existsByMemberId(memberForm.getMemberId()) == true) {
			rda.addFlashAttribute("msg", memberForm.getMemberId()+"는 이미 존재하는 ID입니다.");
			return "redirect:/member/joinMember";
		}
		// false 면 회원가입 진행
		//memberForm.getMemberPw() 값을 SHA-256방식으로 암호화
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		Member member = memberForm.toEntity();
		// 관리자가 아닌 유저로 기본값 설정
		member.setMemberRole("ROLE_USER");
		memberRepository.save(member); // entity에 저장 -> 테이블에 행이 추가
		
		return "redirect:/member/login";
	}
	// 로그인
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	// 로그인 액션
	@PostMapping("member/login")
	public String login(HttpSession session, MemberForm memberForm, RedirectAttributes rda) {
		// pw 암호화
		memberForm.setMemberPw(SHA256Util.encoding(memberForm.getMemberPw()));
		// 로그인 확인 메서드
		MemberMapping loginMember = memberRepository.findByMemberIdAndMemberPw(memberForm.getMemberId(), memberForm.getMemberPw());
		
		if(loginMember == null) {
			log.debug("로그인실패");
			rda.addFlashAttribute("msg", "로그인 실패");
			return "redirect:/member/login";
		}
		//로그인 성공 코드 구현
		log.debug("로그인성공");
		session.setAttribute("loginMember", loginMember); // pw정보까지 모두 session에 저장 --> 나중에는 이렇게 하면 안됨
		return "redirect:/member/memberList";
	}
	
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login";
	}
	
	// 회원정보수정
	@GetMapping("member/modifyMemberPw")
	public String modifyMemberPw(HttpSession session) {
		return "member/modifyMemberPw";
	}
	@PostMapping("member/modifyMemberPw")
	public String modifyMemberPw(HttpSession session, @RequestParam(value = "nowPw") String nowPw
								, @RequestParam(value = "nextPw1") String nextPw1
								, @RequestParam(value = "nextPw2") String nextPw2
								, RedirectAttributes rda) {
		
		MemberMapping loginMember = (MemberMapping) session.getAttribute("loginMember");
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		
		// 모든 값이 입력되었는지 확인
	    if (nowPw.isBlank() || nextPw1.isBlank() || nextPw2.isBlank()) {
	        rda.addFlashAttribute("msg", "모든 항목을 입력하세요.");
	        return "redirect:/member/modifyMemberPw";
	    }
		
		// 현재비밀번호 뽑아오기
		Member member = memberRepository.findByMemberId(loginMember.getMemberId());
		
		// 현재 비밀번호는 암호화된 상태이므로 현재 비밀번호와 비교할땐 암호화 필요
	    // 현재 비밀번호 일치 여부 확인
	    if (!member.getMemberPw().equals(SHA256Util.encoding(nowPw))) {
	        rda.addFlashAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
	        return "redirect:/member/modifyMemberPw";
	    }
	    
	    // 현재 비밀번호와 새 비밀번호 일치 여부 확인
	    if(member.getMemberPw().equals(SHA256Util.encoding(nextPw1))) {
	    	rda.addFlashAttribute("msg", "현재 비밀번호와 새 비밀번호가 일치합니다.");
	    	return "redirect:/member/modifyMemberPw";
	    }

	    // 새 비밀번호 확인
	    if (!nextPw1.equals(nextPw2)) {
	        rda.addFlashAttribute("msg", "새 비밀번호가 일치하지 않습니다.");
	        return "redirect:/member/modifyMemberPw";
	    }

	    // 새 비밀번호 암호화 후 저장
	    member.setMemberPw(SHA256Util.encoding(nextPw1));
	    memberRepository.save(member);
	    
	    // 세션아웃 후 로그인 화면으로
	    session.invalidate();
		return "redirect:/member/login";
	}
	
	// 회원목록
	@GetMapping("/member/memberList")
	public String memberList(Model model
							, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage
							, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
							, @RequestParam(value = "word", defaultValue = "") String word
							, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
			
		Sort sort = Sort.by("memberNo").descending();
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort);
		Page<MemberMapping> list = memberRepository.findByMemberIdContaining(word, pageable);
		
		// Page의 추가 속성
		log.debug("list.getTotalElements(): "+ list.getTotalElements()); // 전체 행의 사이즈
		log.debug("list.getTotalPages(): "+ list.getTotalPages()); // 전체 페이지 사이즈 lastPage
		log.debug("list.getNumber(): "+ list.getNumber()); // 현재 페이지
		log.debug("list.getSize(): "+ list.getSize()); // rowPerPage
		log.debug("list.isFirst(): "+ list.isFirst()); // 1페이지 인지 : 이전링크유무
		log.debug("list.hasNext(): "+ list.hasNext()); // 다음이 있는지 : 다음링크유무

		model.addAttribute("word", word);
		model.addAttribute("list", list);
		model.addAttribute("lastPage", list.getTotalPages()-1);
		model.addAttribute("prevPage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		
		
		return "member/memberList";
	}
	
	//회원탈퇴
	@GetMapping("/member/removeMember")
	public String removeMember(HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}	
		return "member/removeMember";
	}
	
	@PostMapping("member/removeMember")
	public String removeMember(HttpSession session, @RequestParam(value="memberPw") String memberPw, RedirectAttributes rda) {
		
		MemberMapping loginMember = (MemberMapping) session.getAttribute("loginMember");
		Member member = memberRepository.findByMemberId(loginMember.getMemberId());
		if(member.getMemberPw().equals(SHA256Util.encoding(memberPw))) {
			memberRepository.delete(member);
		} else {
			rda.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/member/removeMember";
		}
		return "redirect:/member/login";
	}
	
	
}

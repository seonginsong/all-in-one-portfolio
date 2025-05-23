package com.example.signapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignappController {
	// 문서 페이지
	@GetMapping({"/", "/docView"})
	public String docView() {
		// 문서 정보를 모델에 담아서 렌더링
		return "docView";
	}
	
	// 사인 페이지
	@GetMapping("/signLevel3")
	public String signLevel3() {
		return "signLevel3";
	}
	
	// 사인 페이지
	@GetMapping("/signLevel2")
	public String signLevel2() {
		return "signLevel2";
	}
}

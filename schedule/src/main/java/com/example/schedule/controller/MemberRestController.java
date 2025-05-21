package com.example.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.dto.PwHistory;
import com.example.schedule.service.IMemberService;

@RestController
public class MemberRestController {
	@Autowired IMemberService memberService;
	
	@GetMapping("/isId/{id}")
	public boolean isId(@PathVariable String id) {
		boolean result;
		if(memberService.selectId(id) != null) {
			return true;
		}
		return false;
	}
	
	@PostMapping("/checkPw")
	public boolean checkPw(@RequestBody PwHistory ph) {
		boolean result;
		if(memberService.checkPw(ph) != null) {
			return true;
		}
		return false;
	}
	
}

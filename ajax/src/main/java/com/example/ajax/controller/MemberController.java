package com.example.ajax.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ajax.mapper.MemberMapper;

@Controller
public class MemberController {
	@Autowired MemberMapper memberMapper;
	
	@GetMapping("/joinMember")
	public String joinMember() {
		return "joinMember";
	}
	
	@PostMapping("/joinMember")
	public String joinMember(@RequestParam String id
							, @RequestParam String pw
							, @RequestParam String gender
							, @RequestParam int age) {
		
		Map<String, Object> Map = new HashMap<>();
		Map.put("id", id);
		Map.put("pw", pw);
		Map.put("gender", gender);
		Map.put("age", age);

		memberMapper.insertMember(Map);
		return "redirect:/joinMember";
	}
	
}

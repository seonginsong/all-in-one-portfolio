package com.example.jpaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardHomeController {
	@GetMapping("/boardHome")
	public String home(Model model) {
		model.addAttribute("loginName", "BoardAdmin");
		log.debug("loginName: "+model.getAttribute("loginName"));
		return "boardHome";
	}
}

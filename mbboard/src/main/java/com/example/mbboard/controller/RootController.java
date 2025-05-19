package com.example.mbboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mbboard.service.IRootService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RootController {
	@Autowired IRootService rootService;
	
	@GetMapping("/index")
	public String index(Model model) {
		// 전체 누적 접속자수
		// 멤버 누적 접속자수
		// 관리자 누적 접속자수
		// 금일 전체 접속수
		// 금일 멤버 접속수
		// 금일 관리자 접속수
		Map<String, Integer> connectCountMapAll = rootService.getConnectCountAll();
		Map<String, Integer> connectCountMapToday = rootService.getConnectCountToday();
		model.addAttribute("connectCountMapAll", connectCountMapAll);
		model.addAttribute("connectCountMapToday", connectCountMapToday);
		return "index";
	}
}

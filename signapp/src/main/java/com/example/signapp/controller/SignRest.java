package com.example.signapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signapp.dto.SignForm;
import com.example.signapp.service.SignService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // Controller + ResponseBoy
public class SignRest {
	@Autowired SignService signService;
	@PostMapping("/addSign")
	public String addSign(SignForm signForm) {
		log.info(signForm.toString());
		// 이미지 저장 service - DB에 저장 mapper
		signService.addSign(signForm);
		return "결제완료";
	}
}

package com.example.signapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signapp.dto.SignForm;
import com.example.signapp.service.SignService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // controller + responsebody
public class SignRest {
	@Autowired SignService signService;
	@PostMapping("/addSign")
	public String addSign(SignForm signForm) {
		log.info(signForm.toString());
		// service - mapper 를 통해서 db에 저장
		signService.addSign(signForm);
		return "결제완료";
	}
}

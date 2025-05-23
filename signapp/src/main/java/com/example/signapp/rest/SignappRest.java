package com.example.signapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signapp.dto.SignappForm;
import com.example.signapp.service.SignappService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // controller + responsebody
public class SignappRest {
	@Autowired SignappService signappService;
	@PostMapping("/addSign")
	public String addSign(SignappForm signappForm) {
		log.info(signappForm.toString());
		// service - mapper 를 통해서 db에 저장
		signappService.addSign(signappForm);
		return "결제완료";
	}
}

package com.example.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController {
	@PostMapping("/ajaxRequest")
	public String ajaxRequest(@RequestParam String str
							, @RequestParam int num) {
		System.out.println(+ num);
		return "";
	}
}

package com.example.supa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired NowMapper nowMapper;
	
	@GetMapping("/")
	public String home(Model model) {
		String now = nowMapper.selectNow();
		model.addAttribute("now", now);
		return "home";
	}
}

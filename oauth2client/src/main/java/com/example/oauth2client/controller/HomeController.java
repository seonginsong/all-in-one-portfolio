package com.example.oauth2client.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String Home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// authentication(인증된 UserDetails DTO name)
		String loginUsername = authentication.getName();
		// authentication(인증된 UserDetails DTO role)
		Collection<? extends GrantedAuthority> roleList = authentication.getAuthorities();
		// 순서가 있는(for each가능한) Collection으로 변경
		Iterator<? extends GrantedAuthority> iterator = roleList.iterator();
		
		GrantedAuthority gh = null;
		
		/* 하나의 role밖에 필요없기 때문에 반복문 쓸 필요 x
		while((gh=iterator.next()) != null) {
			String role = gh.getAuthority(); // 사용자의 role
		}
		*/
		String loginRole = "";
		if((gh = iterator.next()) != null) {
			loginRole = gh.getAuthority();
		}
		model.addAttribute("loginUsername", loginUsername);
		model.addAttribute("loginRole", loginRole);
		
		return "home";
	}
}

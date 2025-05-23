package com.example.signapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.signapp.dto.Employee;
import com.example.signapp.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired LoginService loginService;

    @GetMapping({ "/", "/login" })
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute Employee employee, HttpServletResponse response, Model model) {
        Employee loginEmployee = loginService.login(employee);

        if (loginEmployee == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }

  
        session.setAttribute("loginEmployee", loginEmployee);
        session.setAttribute("loginName", loginEmployee.getEmployeeName()); 

        return "redirect:/login";  
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/login"; 
    }

    @GetMapping("/joinEmployee")
    public String joinEmployeeForm() {
        return "joinEmployee";
    }

    @PostMapping("/joinEmployee")
    public String joinEmployee(@ModelAttribute Employee employee) {
        loginService.joinEmployee(employee);
        return "redirect:/login";
    }
}

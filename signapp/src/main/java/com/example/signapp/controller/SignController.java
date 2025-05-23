package com.example.signapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.signapp.dto.Document;
import com.example.signapp.dto.Employee;
import com.example.signapp.service.DocumentService;

import jakarta.servlet.http.HttpSession;
@Controller
public class SignController {
	
	@Autowired DocumentService documentService;

	@GetMapping("/signLevel3")
	public String signLevel3() {
		return "signLevel3";
	}
	

	@GetMapping("/docView")
	public String docView() {
	
		return "docView";
	}

    @GetMapping("/docWrite")
    public String showWriteForm(Model model, Document document) {
        model.addAttribute("document", document);
        return "docWrite";  
    }

 
    @PostMapping("/writeDocument")
    public String writeDocument(@ModelAttribute Document document, HttpSession session) {
        // 로그인한 직원 정보 세션에서 꺼내기
        Employee loginEmployee = (Employee) session.getAttribute("loginEmployee");
        if (loginEmployee == null) {
            return "redirect:/login";  
        }

 
        document.setEmployeeId(loginEmployee.getEmployeeId());

        // 문서 저장
        documentService.writeDocument(document);

        return "redirect:/docList";  
    }
    @GetMapping("/docList")
    public String docList(Model model) {
        List<Document> documentList = documentService.getDocumentList();
        model.addAttribute("documentList", documentList);
        return "docList";
    }
}

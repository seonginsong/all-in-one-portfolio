package com.example.springai.api;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springai.dto.ChatDto;
import com.example.springai.service.AIChatService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AIChatController {
	private final AIChatService aIChatService;
	public AIChatController(AIChatService aIChatService) {
		this.aIChatService = aIChatService;
	}
	
	@PostMapping({"/","/chat"})
	public String chat(@RequestBody Map<String, String> body, HttpSession session) { // JSON문자열 {"userMsg":"hi", "", ...} -> java의 DTO객체로 바꿔줄 필요가 있음 => responsebody
		ChatDto chatDto = new ChatDto();
		System.out.println(session.getId());
		String userMsg = body.get("userMsg");
		String aiReply = aIChatService.generate(userMsg, session);
		
		chatDto.setSessionId(session.getId());
		chatDto.setUserChat(userMsg);
		chatDto.setAiChat(aiReply);
		
		aIChatService.saveChat(chatDto);
		
		return aiReply;
	}
}

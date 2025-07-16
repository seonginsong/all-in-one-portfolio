package com.example.springai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import com.example.springai.dto.ChatDto;
import com.example.springai.mapper.ChatMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class AIChatService {
	private OpenAiChatModel openAiChatModel;
	private ChatMapper chatMapper;
	public AIChatService(OpenAiChatModel openAiChatModel, ChatMapper chatMapper) {
		this.openAiChatModel = openAiChatModel;
		this.chatMapper = chatMapper;
	}
	
	// OpenAI서버와 통신할 메서드 선언
	// param String userMsg : 사용자가 보낸 문자열
	// return String : 오픈쳇 서버의 응답 문자열
	public String generate(String userMsg, HttpSession session) {
		
		List<Message> messageList = (List<Message>)session.getAttribute("chatHistory");
		if(messageList == null) {
			messageList = new ArrayList<Message>(); // 이전 이력 없이 처음 대화.
		}
		
		// SystemMessage, UserMessage
		SystemMessage systemMessage = new SystemMessage("너는 한국어로 세상에서 최고로 예의없는 AI 챗봇이다");
		UserMessage userMessage = new UserMessage(userMsg);
		
		messageList.add(systemMessage);
		messageList.add(userMessage);
		
		// 옵션
		OpenAiChatOptions options = OpenAiChatOptions.builder()
			.model("gpt-4.1-mini") // 사용하고자 하는 OpenAI 모델(버전)의 이름을 지정
			.temperature(0.7) // 창의성(무작위성) 정도를 설정(0.0 ~ 2.0)값으로 보통 0~1 사이 사용 0.0 : 법으로 규정된 말만, 높을수록 창의성
			.build();
		
		// 프롬프트 : openAI서버에 전달하는 모델(매개값)
		Prompt prompt = new Prompt(messageList, options);
		
		// openAiChatModel 빈을 통해 prompt를 OpenAI서버에 전달
		ChatResponse res =  this.openAiChatModel.call(prompt);
		String aiReply = res.getResult().getOutput().getText();
		// AI응답 내용들도 messageList에 누적
		AssistantMessage assistantMessage = new AssistantMessage(aiReply);
		messageList.add(assistantMessage);
		
		// messageList 변경된 내용 session의 messageList에도 반영
		session.setAttribute("chatHistory", messageList);
		
		return aiReply;
	}
	
	public void saveChat(ChatDto chatDto) {
		chatMapper.saveChat(chatDto);
	}
}

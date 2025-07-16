package com.example.springai.dto;

import lombok.Data;

@Data
public class ChatDto {
	private int chatNo;
	private String sessionId;
	private String userChat;
	private String aiChat;
}

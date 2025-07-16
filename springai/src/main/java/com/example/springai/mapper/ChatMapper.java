package com.example.springai.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springai.dto.ChatDto;
@Mapper
public interface ChatMapper {
	void saveChat(ChatDto chatDto);
}

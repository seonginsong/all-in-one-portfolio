package com.example.supa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.supa.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoard();
	int insertBoard(String title, String content);
	int updateBoard(String title, String content, Integer id);
	int deleteBoard(int id);
}

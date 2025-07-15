package com.example.supa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supa.dto.BoardDto;
import com.example.supa.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired BoardMapper boardMapper;
	
	public List<BoardDto> selectBoard() {
		return boardMapper.selectBoard();
	}
	
	public int insertBoard(String title, String content) {
		return boardMapper.insertBoard(title, content);
	}
	
	public int updateBoard(String title, String content, Integer id) {
		return boardMapper.updateBoard(title, content, id);
	}
	
	public int deleteBoard(int id) {
		return boardMapper.deleteBoard(id);
	}
}

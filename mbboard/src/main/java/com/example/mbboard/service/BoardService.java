package com.example.mbboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;
import com.example.mbboard.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BoardService implements IBoardService {
	@Autowired BoardMapper boardMapper; // 인터페이스 형태로 의존성 주입 -> 디커풀링
	public List<Board> selectBoardList(Page p) {
		return boardMapper.selectBoardListByPage(p);
	}
	public int selectCnt(String searchWord) {
		 if (searchWord != null && !searchWord.trim().isEmpty()) {
		        searchWord = searchWord.trim();
		    } else {
		        searchWord = null;
		    }
		return boardMapper.selectCnt(searchWord);
	}
	public Board selectBoardOne(Board board) {
		return boardMapper.selectBoardOne(board);
	}
	public int updateBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	public int insertBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	public int deleteBoard(Board board) {
		return boardMapper.deleteBoardByKey(board);
	}
}

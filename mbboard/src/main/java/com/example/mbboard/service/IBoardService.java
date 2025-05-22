package com.example.mbboard.service;

import java.util.List;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;

public interface IBoardService {
	List<Board> selectBoardList(Page p);
	int selectCnt(String searchWord);
	Board selectBoardOne(Board board);
	int updateBoard(Board board);
	int insertBoard(Board board);
	int deleteBoard(Board board);
}

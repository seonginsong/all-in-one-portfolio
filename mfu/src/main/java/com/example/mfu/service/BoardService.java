package com.example.mfu.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;
import com.example.mfu.dto.Boardfile;
import com.example.mfu.exception.AddBoardException;
import com.example.mfu.mapper.BoardMapper;
import com.example.mfu.mapper.BoardfileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class BoardService {
	@Autowired BoardMapper boardMapper;
	@Autowired BoardfileMapper boardfileMapper;
	
	public void addBoard(BoardForm boardForm) {
		// 1) 파일저장
		Board board = new Board();
		board.setBoardTitle(boardForm.getBoardTitle()); // board.getBoardNo() == 0
		int addBoardRow = boardMapper.insertBoard(board); // After ==> board.setBoardNo(key);
		log.info("board.getBoardNo(): " + board.getBoardNo());
		if(addBoardRow != 1) {
			throw new AddBoardException();
		}
		// 2) board 추가
		if(boardForm.getBoardfile() != null) {
		for(MultipartFile f : boardForm.getBoardfile()) {
			// getBoardfile size만큼 입력
			Boardfile bf = new Boardfile();
			bf.setBoardNo(board.getBoardNo());
			bf.setFiletype(f.getContentType());
			String original = f.getOriginalFilename(); // 원래 파일명
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String filename = uuid + "__" + original; // 나중에 원래 이름 꺼낼 수 있음
			bf.setFilename(filename);
			int addBoardfileRow = boardfileMapper.insertBoardfile(bf);
			if(addBoardfileRow != 1) {
				throw new AddBoardException();
			}
			// 3) boardfile 추가
				// getBoardfile size만큼 파일저장
				File emptyfile = new File("c:/project/upload/"+filename);
				// f 안에 파일스트림을 emptyFile로 이동
				try {
					f.transferTo(emptyfile);
				} catch (Exception e) {
					throw new AddBoardException(); // 강제하는 예외를 강제하지 않는 예외로 변경 -> 예외 발생 -> transaction
				}
			}
		}
	}
	
	public List<Board> getBoardList() {
		// getAll()은 중계역할
		return boardMapper.selectBoardList();
	}
	
	public List<Boardfile> getFileList(int boardNo) {
		
		return boardfileMapper.selectFileList(boardNo);
	}
	}

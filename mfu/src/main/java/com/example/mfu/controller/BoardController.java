package com.example.mfu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;
import com.example.mfu.dto.Boardfile;
import com.example.mfu.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired BoardService boardService;
	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		log.info(boardForm.toString());
		boardService.addBoard(boardForm);
		return "redirect:/";
	}
	
	@GetMapping({"/", "/boardList"})
	public String boardList(Model model) {
		List<Board> list = boardService.getBoardList();
		model.addAttribute("list", list);
		return "boardList";
	}
	
	@GetMapping("/boardOne")
	public String boardOne(Model model, int boardNo) {
		List<Boardfile> list = boardService.getFileList(boardNo);
		 log.info("boardNo: {}", boardNo);
		    log.info("파일 목록: {}", list);
		model.addAttribute("list", list);
		return "boardOne";
	}
}

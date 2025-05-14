package com.example.mbboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;
import com.example.mbboard.service.BoardService;
import com.example.mbboard.service.IBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService_1;
	@Autowired IBoardService boardService;

    BoardController(BoardService boardService_1) {
        this.boardService_1 = boardService_1;
    }
	
	@GetMapping({"/", "/boardList"})
	public String boardList(@RequestParam(required = false) String searchWord
							, Model model
							, @RequestParam(defaultValue = "1") int currentPage) {
		if (searchWord != null && !searchWord.trim().isEmpty()) {
		    searchWord = searchWord.trim();
		} else {
		    searchWord = null;
		};
		System.out.println("최종 searchWord: " + searchWord);
		int totalCnt = boardService.selectCnt(searchWord);
		Page p = new Page(10, currentPage, totalCnt, searchWord);

		List<Board> list = boardService.selectBoardList(p);
		model.addAttribute("list", list);
		model.addAttribute("p", p);
		return "boardList";
	}
	
	@GetMapping("/boardOne")
	public String boardOne(@RequestParam int boardNo, Model model) {
        Board board = new Board();
        board.setBoardNo(boardNo);
        Board b = boardService.selectBoardOne(board);
        model.addAttribute("board", b);
        return "boardOne"; 
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(@RequestParam int boardNo, Model model) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		Board b = boardService.selectBoardOne(board);
		model.addAttribute("board", b);
		return "updateBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@RequestParam String boardTitle
							,@RequestParam String boardContent
							,@RequestParam String boardUser
							,@RequestParam int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setBoardUser(boardUser);
		boardService.updateBoard(board);
		
		return "redirect:/boardOne?boardNo=" + board.getBoardNo();
	}
	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(@RequestParam String boardTitle
							,@RequestParam String boardContent
							,@RequestParam String boardUser) {
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setBoardUser(boardUser);
		
		boardService.insertBoard(board);
		return "redirect:/boardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@RequestParam int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		boardService.deleteBoard(board);
		return "redirect:/boardList";
	}
}

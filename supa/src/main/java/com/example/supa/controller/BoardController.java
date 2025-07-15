package com.example.supa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.supa.dto.BoardDto;
import com.example.supa.service.BoardService;

@Controller
public class BoardController {
	@Autowired BoardService boardService;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardDto> list = boardService.selectBoard();
		model.addAttribute("list", list);
		return "boardList";
	}
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	@PostMapping("/addBoard")
	public String addBoard(@RequestParam String title, @RequestParam String content, Model model) {
		int row = 0;
		if(title.trim().isEmpty() || content.trim().isEmpty()) {
			model.addAttribute("msg", row);
			return "addBoard";
		}
		row = boardService.insertBoard(title, content);
		return "redirect:/boardList";
	}
	@GetMapping("/editBoard")
	public String editBoard(Integer id, Model model) {
		model.addAttribute("id", id);
		return "editBoard";
	}
	@PostMapping("/editBoard")
	public String editBoard(@RequestParam String title, @RequestParam String content, Model model, @RequestParam Integer id) {
		int row = 0;
		if(title.trim().isEmpty() || content.trim().isEmpty()) {
			return "redirect:/editBoard?id="+id;
		}
		row = boardService.updateBoard(title, content, id);
		return "redirect:/boardList";
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(Integer id) {
		int row = boardService.deleteBoard(id);
		return "redirect:/boardList";
	}
}

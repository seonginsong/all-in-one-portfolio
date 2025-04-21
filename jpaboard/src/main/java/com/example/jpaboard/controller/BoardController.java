package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jpaboard.dto.BoardForm;
import com.example.jpaboard.entity.Board;
import com.example.jpaboard.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/board/addBoard")
	public String newBoardForm() {
		return "board/addBoard";
	}
	
	@PostMapping("/board/create")
	public String createBoard(BoardForm form) {
		System.out.println(form.toString());
		
		Board entity = form.toEntity();
		boardRepository.save(entity);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("board/boardList")
	public String list(Model model
						, @RequestParam(defaultValue = "0") int currentPage
						, @RequestParam(defaultValue = "10") int rowPerPage
						, @RequestParam(defaultValue = "") String word) {
		Sort sort = Sort.by("no").descending();
		
		PageRequest page = PageRequest.of(currentPage, rowPerPage, sort);
		Page<Board> list = boardRepository.findByTitleContaining(page, word);
		
		// Page의 추가 속성
		log.debug("list.getTotalElements(): "+ list.getTotalElements()); // 전체 행의 사이즈
		log.debug("list.getTotalPages(): "+ list.getTotalPages()); // 전체 페이지 사이즈 lastPage
		log.debug("list.getNumber(): "+ list.getNumber()); // 현재 페이지
		log.debug("list.getSize(): "+ list.getSize()); // rowPerPage
		log.debug("list.isFirst(): "+ list.isFirst()); // 1페이지 인지 : 이전링크유무
		log.debug("list.hasNext(): "+ list.hasNext()); // 다음이 있는지 : 다음링크유무

		model.addAttribute("word", word);
		model.addAttribute("list", list);
		model.addAttribute("lastPage", list.getTotalPages()-1);
		model.addAttribute("prevPage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		
		return "board/boardList";
	}
	
	@GetMapping("/board/boardOne")
	public String one(Model model, @RequestParam Integer no) {
		Board board = boardRepository.findById(no).orElse(null);
		model.addAttribute("board", board);
		return "board/boardOne";
	}
	
	@GetMapping("/board/modifyBoard")
	public String modify(Model model, @RequestParam Integer no) {
		Board board = boardRepository.findById(no).orElse(null);
		model.addAttribute("board", board);
		return "board/modifyBoard";
	}
	
	
	@PostMapping("/board/update")
	public String update(BoardForm boardForm) {
		Board board = boardForm.toEntity();
		boardRepository.save(board);
		
		return "redirect:/board/boardOne?no="+board.getNo();
	}
	
	@GetMapping("board/deleteBoard")
	public String deleteBoard(@RequestParam Integer no, RedirectAttributes rdb) {
		Board board = boardRepository.findById(no).orElse(null);
		
		if(board == null) {
			rdb.addFlashAttribute("msg", "삭제실패");
			return "redirect:/board/show?no="+no;
		} else {
			boardRepository.delete(board);
			rdb.addFlashAttribute("msg", "삭제성공");
			return "redirect:/board/boardList";
		}
	}
	
	
	
	
	
}

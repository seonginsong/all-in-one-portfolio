package com.example.fileupload.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardRepository;
import com.example.fileupload.repository.BoardfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardfileController {
	@Autowired
	BoardfileRepository boardfileRepository;
	@Autowired
	BoardRepository boardRepository;
	
	@GetMapping("/removeBoardfile")
	public String removeBoardfile(@RequestParam(value="fno") int fno
									, @RequestParam(value="bno") int bno
									, Model model){
		model.addAttribute("fno", fno);
		model.addAttribute("bno", bno);
		return "removeBoardfile";
	}
	
	@PostMapping("/removeBoardfile")
	public String removeBoardfile(@RequestParam(value="fno") int fno
									, @RequestParam(value="bno") int bno
									, @RequestParam(value="pw") String pw
									, RedirectAttributes rda) {
		// 순서 : 물리적인 파일 삭제 후 테이블 row 삭제
		Board board = boardRepository.findById(bno).orElse(null);
		log.debug("pw : "+pw);
		log.debug("board.getPw() : "+ board.getPw());
		
		if(!pw.equals(board.getPw())) {
			rda.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/removeBoardfile?fno="+fno+"&bno="+bno;
		} else {
		Boardfile boardfile = boardfileRepository.findById(fno).orElse(null);
		File f = new File("c:/project/upload/"+boardfile.getFname()+"."+boardfile.getFext());
		if(f.exists()) {
			f.delete();
		}

		// 테이블 row 삭제
		boardfileRepository.deleteById(fno);
		return "redirect:/boardOne?bno="+bno;
		}
	}
}

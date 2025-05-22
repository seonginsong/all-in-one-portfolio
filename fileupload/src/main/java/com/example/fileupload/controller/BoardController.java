package com.example.fileupload.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fileupload.dto.BoardForm;
import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;
import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardRepository;
import com.example.fileupload.repository.BoardfileRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class BoardController {
	
	@Autowired BoardRepository boardRepository;
	@Autowired BoardfileRepository boardfileRepository;
	
	// 입력폼
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	// 입력액션
	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		log.debug(boardForm.toString());
		// 이슈 : 파일을 첨부하지 않아도 filesize가 1
		log.debug("size :" + boardForm.getFileList().size());
		
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setPw(boardForm.getPw());
		boardRepository.save(board); // board 저장
		int bno = board.getBno(); // board insert 후 bno 변경되었는지
		log.debug("bno : "+bno);
		
		// 파일 분리
		List<MultipartFile> fileList = boardForm.getFileList();
		long firstFileSize = fileList.get(0).getSize();
		log.debug("firstFileSize : "+firstFileSize);
		
		if(firstFileSize > 0) { // 첫번째 파일 사이즈가 0 초과이면 첨부된 파일이 있다.
			// 업로드 파일 유효성 검증 코드 구현
			for(MultipartFile f : fileList) {
				if(f.getContentType().equals("application/octet-stream") || f.getSize() > 10*1024*1024) { //1byte*1024=1kbyte, 1kbyte*1024=1mbyte
					log.debug("10메가바이트보다 크거나 실행파일이면 업로드가 불가능합니다.");
					return "redirect:/addBoard"; // Msg 추가해서 보내기도 가능
				}
			}
			// 검증 코드를 통과한 경우 업로드 진행
			for(MultipartFile f : fileList) {
				log.debug("파일타입"+f.getContentType());
				log.debug("오리지날네임"+f.getOriginalFilename());
				log.debug("파일사이즈"+f.getSize());
				// 확장자 추출
				String ext = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")+1);
				log.debug("확장자 : "+ext);
				String fName = UUID.randomUUID().toString().replace("-", "");
				log.debug("saveName : "+fName);
				
				File emptyFile = new File("c:/project/upload/"+fName+"."+ext);
				// f의 byte를 String 형태로 emptyFile에 복사
				try {
					f.transferTo(emptyFile);
				} catch (Exception e) {
					log.error("파일저장실패");
					e.printStackTrace();
				}
				
				// boardfile테이블에도 파일정보 저장
				Boardfile boardfile = new Boardfile();
				boardfile.setBno(bno);
				boardfile.setFext(ext);
				boardfile.setFname(fName);
				boardfile.setForiginname(f.getOriginalFilename());
				boardfile.setFsize(f.getSize());
				boardfile.setFtype(f.getContentType());
				boardfileRepository.save(boardfile);
			}
		}
		
		return "redirect:/boardList";
	}
	
	// 전체 리스트
	@GetMapping({"/", "boardList"})
	public String boardList(Model model
							, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage
							, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 페이징
		// Sort : bno DESC
		Sort sort = Sort.by("bno").descending();
		// PageRequest : 0, 10
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort);
		// Page<BoardMapping>
		Page<BoardMapping> list = boardRepository.findAllBoardList(pageable);
		// Page의 추가 속성
		log.debug("list.getTotalElements(): "+ list.getTotalElements()); // 전체 행의 사이즈
		log.debug("list.getTotalPages(): "+ list.getTotalPages()); // 전체 페이지 사이즈 lastPage
		log.debug("list.getNumber(): "+ list.getNumber()); // 현재 페이지
		log.debug("list.getSize(): "+ list.getSize()); // rowPerPage
		log.debug("list.isFirst(): "+ list.isFirst()); // 1페이지 인지 : 이전링크유무
		log.debug("list.hasNext(): "+ list.hasNext()); // 다음이 있는지 : 다음링크유무

		model.addAttribute("lastPage", list.getTotalPages()-1);
		model.addAttribute("prevPage", list.getNumber()-1);
		model.addAttribute("nextPage", list.getNumber()+1);
		
		model.addAttribute("list", list);
		return "boardList";	
	}
		
	// board상세보기
	@GetMapping("/boardOne")
	public String boardOne(Model model, @RequestParam(value = "bno") int bno) {
		BoardMapping boardMapping = boardRepository.findByBno(bno);
		
		List<Boardfile> fileList = boardfileRepository.findByBno(bno);
		log.debug("size :"+fileList.size());
		model.addAttribute("boardMapping", boardMapping);
		model.addAttribute("fileList", fileList);
		return "boardOne";
	}
	
	// board 수정폼
	@GetMapping("/modifyBoard")
	public String modifyboard(Model model, @RequestParam(value="bno") int bno) {
		BoardMapping boardMapping = boardRepository.findByBno(bno);
		model.addAttribute("boardMapping", boardMapping);
		return "modifyBoard";
	}
	
	// board 수정액션
	@PostMapping("/modifyBoard")
	public String modifyboard(@RequestParam(value="bno") int bno
								,@RequestParam(value="title") String title
								,@RequestParam(value="pw") String pw
								, RedirectAttributes rda) {
		Board board = boardRepository.findById(bno).orElse(null);
		if(!board.getPw().equals(pw)) {
			rda.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/modifyBoard?bno="+bno;
		}
		board.setTitle(title);
		boardRepository.save(board);
		return "redirect:/boardOne?bno="+bno;
	}
	
	// board 삭제 폼
	@GetMapping("removeBoard")
	public String removeboard(Model model, @RequestParam(value="bno") int bno, RedirectAttributes rda) {
		int cnt = 0;
		cnt = boardfileRepository.getCountFnoByBno(bno);
		log.debug("cnt :"+cnt);
		if(cnt > 0) {
			rda.addFlashAttribute("msg", "첨부파일이 존재해 삭제할 수 없습니다.");
			return "redirect:/boardOne?bno="+bno;
		}
		model.addAttribute("bno", bno);
		return "removeBoard";
	}
	
	// board 삭제 액션
	@PostMapping("removeBoard")
	public String removeBoard(@RequestParam(value = "bno") int bno
								, @RequestParam(value = "pw") String pw
								, RedirectAttributes rda) {
		Board board = boardRepository.findById(bno).orElse(null);
		if(!board.getPw().equals(pw)) {
			rda.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/removeBoard?bno="+bno;
		}
		boardRepository.deleteById(bno);
		return "redirect:/boardList";
	}
	
}

package com.example.jpaboard.controller;

import java.util.Map;

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

import com.example.jpaboard.dto.ArticleForm;
import com.example.jpaboard.entity.Article;
import com.example.jpaboard.repository.ArticleRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {
	@Autowired // 의존성 주입
	private ArticleRepository articleRepository;
	
	@GetMapping("/articles/sqlTest")
	public String sqlTest(Model model) {
		Map<String, Object> map = articleRepository.getMinMaxCountByTitle("a%");
		model.addAttribute("map", map);
		log.debug(map.toString());
		return "articles/sqlTest";
	}
	
	@GetMapping("/articles/new") // doGet()
	public String newArticleForm(HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}	
		return "articles/new";
	}
	
	@PostMapping("/articles/create") // doPost()
	public String createArticle(ArticleForm  form) { // @RequestParam, DTO(커멘드객체)
		System.out.println(form.toString());
		
		// controller가 가지고있는 DTO를 Entity 타입으로 변환 필요 -> dto_>entity 과정에서 필드가 많으면 많을수록 변환이 귀찮아짐 -> 하나의 메서드를 dto에 toEntity메서드
		/*Article entity = new Article();
		entity.setTitle(form.getTitle());
		entity.setContent(form.getContent());
		이걸 메서드를 만듦으로써*/
		Article entity = form.toEntity();
		
		articleRepository.save(entity); // repository 호출할때는 entity 필요 - 키값이 entity에 없으면 update
		return "redirect:/articles/index"; // GET호출 /articles/list
	}
	
	@GetMapping("/articles/index")
	public String list(Model model
							, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage
							, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
							, @RequestParam(value = "word", defaultValue = "") String word
							, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		//Sort s1 = Sort.by("title").ascending();
		//Sort s2 = Sort.by("content").descending();
		//Sort sort = s1.and(s2);
		Sort sort = Sort.by("id").descending();
		
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort);
		Page<Article> list = articleRepository.findByTitleContaining(pageable, word);
		
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
		return "articles/index";
	}
	
	@GetMapping("/articles/show")
	public String show(Model model, @RequestParam long id, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		Article article = articleRepository.findById(id).orElse(null);
		model.addAttribute("article", article);
		return "articles/show";
	}
	
	@GetMapping("/articles/edit")
	public String edit(Model model, @RequestParam long id, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		Article article = articleRepository.findById(id).orElse(null);
		model.addAttribute("article", article);
		return "articles/edit";
	}
	
	@PostMapping("articles/update")
	public String update(ArticleForm articleForm, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		Article article = articleForm.toEntity(); // 저장하면 원래 있던 키(id)행을 수정
		// entity가 키값을 가지고있으면 새로운 행 추가가 아닌 수정
		articleRepository.save(article);
		
		return "redirect:/articles/show?id="+article.getId();
	}
	
	@GetMapping("articles/delete")
	public String delete(@RequestParam long id, RedirectAttributes rda, HttpSession session) {
		// session 인증/인가 검사
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/member/login";
		}
		Article article = articleRepository.findById(id).orElse(null);
		
		if(article == null) {
			rda.addFlashAttribute("msg", "삭제실패");
			return "redirect:/articles/show?id="+id;
		} else {
			articleRepository.delete(article);
			rda.addFlashAttribute("msg", "삭제성공"); // index에 redirect로 호출되었다면 RedirectAttributes.addAttribute("msg", "삭제성공") 이 자동으로 추가
			return "redirect:/articles/index";
		}
	}
}

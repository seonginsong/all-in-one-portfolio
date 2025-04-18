package com.example.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jpaboard.dto.ArticleForm;
import com.example.jpaboard.entity.Article;
import com.example.jpaboard.repository.ArticleRepository;

@Controller
public class ArticleController {
	@Autowired // 의존성 주입
	private ArticleRepository articleRepository;
	
	@GetMapping("/articles/new") // doGet()
	public String newArticleForm() {
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
		
		articleRepository.save(entity); // repository 호출할때는 entity 필요
		return "redirect:/"; // "/"으로 redirect
	}
}

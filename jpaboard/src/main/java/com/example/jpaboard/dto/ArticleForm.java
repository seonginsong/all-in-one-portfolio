package com.example.jpaboard.dto;

import com.example.jpaboard.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {
	private String title;
	private String content;
	
	public Article toEntity() {
		Article entity = new Article();
		entity.setTitle(this.title);
		entity.setContent(this.content);
		return entity;
	}
}

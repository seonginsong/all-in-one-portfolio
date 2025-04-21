package com.example.jpaboard.dto;

import com.example.jpaboard.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardForm {
	private Integer no;
	private String title;
	private String content;
	
	public Board toEntity() {
		Board entity = new Board();
		entity.setNo(this.no);
		entity.setTitle(this.title);
		entity.setContent(this.content);
		return entity;
	}
}
